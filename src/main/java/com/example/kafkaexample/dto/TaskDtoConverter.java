package com.example.kafkaexample.dto;

import com.example.kafkaexample.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoConverter {

    public TaskDto convert(Task from) {
        return new TaskDto(
                from.getId(),
                from.getContent()
        );
    }

}
