package com.example.kafkaexample.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "create-task", groupId = "group-id")
    public void consume(String message) {
        // start to send email process

        logger.info(message);
    }

}
