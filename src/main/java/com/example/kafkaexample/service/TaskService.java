package com.example.kafkaexample.service;

import com.example.kafkaexample.dto.CreateTaskRequestDto;
import com.example.kafkaexample.dto.TaskDto;
import com.example.kafkaexample.dto.TaskDtoConverter;
import com.example.kafkaexample.exception.TaskNotFoundException;
import com.example.kafkaexample.kafka.Producer;
import com.example.kafkaexample.model.Task;
import com.example.kafkaexample.repository.TaskRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskDtoConverter taskDtoConverter;
    private final Producer producer;

    public TaskService(TaskRepository taskRepository,
                       TaskDtoConverter taskDtoConverter,
                        Producer producer) {
        this.taskRepository = taskRepository;
        this.taskDtoConverter = taskDtoConverter;
        this.producer = producer;
    }

    public TaskDto createTask(CreateTaskRequestDto request) {
        Task task = taskRepository.save(new Task(null, request.getContent()));
        producer.sendMessage(String.format("Task created with content: %s",request.getContent()));
        return taskDtoConverter.convert(task);
    }

    public TaskDto getById(Long id) {
        return taskDtoConverter.convert(findTaskById(id));
    }

    protected Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Requested task not found with id " + id));
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public void deleteTask(Long id) {
        taskRepository.delete(findTaskById(id));
    }


}
