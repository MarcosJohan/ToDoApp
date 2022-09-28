package com.marcos.ToDoApp.Controller;

import com.marcos.ToDoApp.Persistence.Entity.Task;
import com.marcos.ToDoApp.Persistence.Entity.TaskStatus;
import com.marcos.ToDoApp.Service.Dto.TaskDto;
import com.marcos.ToDoApp.Service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public Task CreateTask(@RequestBody TaskDto taskDto){
        return this.service.createTask(taskDto);
    }

    @GetMapping
    public List<Task> finAll(){
        return this.service.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> findAllTaskStatus(@PathVariable("status") TaskStatus status){
        return this.service.FindAllByTaskStatus(status);
    }

    @PatchMapping("/mark_as_finished/{id}")
    public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id){
        this.service.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.service.DeleteById(id);
        return ResponseEntity.noContent().build();
    }
}
