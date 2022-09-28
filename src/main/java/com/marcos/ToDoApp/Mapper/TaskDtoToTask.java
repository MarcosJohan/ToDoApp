package com.marcos.ToDoApp.Mapper;

import com.marcos.ToDoApp.Persistence.Entity.Task;
import com.marcos.ToDoApp.Persistence.Entity.TaskStatus;
import com.marcos.ToDoApp.Service.Dto.TaskDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskDtoToTask implements IMapper<TaskDto, Task>{

    @Override
    public Task map(TaskDto in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setTaskStatus(TaskStatus.ON_TIME);
        task.setFinished(false);
        return task;
    }
}
