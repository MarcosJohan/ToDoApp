package com.marcos.ToDoApp.Service;

import com.marcos.ToDoApp.Exceptions.ToDoException;
import com.marcos.ToDoApp.Mapper.TaskDtoToTask;
import com.marcos.ToDoApp.Persistence.Entity.Task;
import com.marcos.ToDoApp.Persistence.Entity.TaskStatus;
import com.marcos.ToDoApp.Persistence.Repository.TaskRepository;
import com.marcos.ToDoApp.Service.Dto.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskDtoToTask mapper;

    public TaskService(TaskRepository repository, TaskDtoToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskDto taskDto){
        Task task = mapper.map(taskDto);
        return this.repository.save(task);
    }

    public List<Task> findAll(){
       return this.repository.findAll();
    }

    public List<Task> FindAllByTaskStatus(TaskStatus status){
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id){
        Optional<Task> taskOptional = this.repository.findById(id);
        if (taskOptional.isEmpty()) throw new ToDoException("Task not Found", HttpStatus.NOT_FOUND);
        this.repository.markTaskAsFinished(id);
    }

    public void DeleteById(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()) throw new ToDoException("Task not Found", HttpStatus.NOT_FOUND);
        this.repository.deleteById(id);
    }
}
