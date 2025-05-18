package com.as.kafka.csr;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserAuthConsumer {

    @KafkaListener(topics = "user-topic", groupId = "user-auth-service")
    public void consumeMessageForPrimaryGroup(ConsumerRecord<String, String> record) {
        String key = record.key();
        String message = record.value();

        if (key == null || message == null || key.isBlank() || message.isBlank()) {
            log.warn("Invalid message received.");
            return;
        }

        log.info("Received  message in user auth service: key={}, value={}, partition={}, offset={}",
                record.key(), message, record.partition(), record.offset());

        if (!key.toLowerCase().contains("jwt")) {
            log.info("Authentication failed. Missing JWT in message: {}", message);
            return;
        }

            try {
                // Simulate JWT validation
                log.info("Validating JWT token for message: {}", message);
                Thread.sleep(200); //Simulate latency due to DB call

                // Simulate successful authentication
                log.info("User authenticated successfully");

                // Simulate operation that can be done after authentication. Transform message (e.g., uppercase for demo)
                String transformedMessage = message.toUpperCase();
                log.info("Applying transformation: [=={}==]", transformedMessage);
            } catch (Exception e) {
                log.error("Failed to process message: {}. Error: {}", message, e.getMessage());
                // In a real app, you might send to a dead-letter topic or retry
            }
    }
}