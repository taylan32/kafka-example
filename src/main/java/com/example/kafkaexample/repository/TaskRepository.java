package com.example.kafkaexample.repository;

import com.example.kafkaexample.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
