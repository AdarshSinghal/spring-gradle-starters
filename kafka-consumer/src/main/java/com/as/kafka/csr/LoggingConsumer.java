package com.as.kafka.csr;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoggingConsumer {
    @KafkaListener(topics = "user-topic", groupId = "logging-service")
    public void consumeMessageForLogging(ConsumerRecord<String, String> record) {

        String key = record.key();
        String message = record.value();

        if (key ==null || message == null || key.isBlank() || message.isBlank()) {
            log.warn("Invalid message received.");
            return;
        }

        if (key.toLowerCase().contains("jwt")) {
            log.info("User performed operation using JWT");
            return;
        }

        log.info("Received  message in logging service: key={}, value={}, partition={}, offset={}",
                record.key(), message, record.partition(), record.offset());

        try {

            log.info("Persisting message to logging database: {}", message);
            // Simulate saving to a database
            Thread.sleep(300);
            if (message.contains("error")) {
                log.error("Database error");
                return;
            }
            log.info("Message logged successfully: {}", message);
        } catch (Exception e) {
            log.error("Failed to process message: {}. Error: {}", message, e.getMessage());
            // In a real app, you might send to a dead-letter topic or retry
        }
    }
}