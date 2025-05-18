package com.as.kafka.csr;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ActivityConsumer {

    private static final String DLT_TOPIC = "user-activity-dlt";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "user-activity", groupId = "activity-service")
    public void consumeActivityMessage(ConsumerRecord<String, String> record) {

        String key = record.key();
        String message = record.value();

        if (key ==null || message == null || key.isBlank() || message.isBlank()) {
            log.warn("Invalid message received.");
            return;
        }

        log.info("Received message in user activity service: key={}, value={}, partition={}, offset={}",
                record.key(), message, record.partition(), record.offset());


        if (message.length() > 10) {
            log.warn("Message too long: key={}, length={}. Sending to DLT.", record.key(), message.length());
            kafkaTemplate.send(DLT_TOPIC, record.key(), message);
            return;
        }


        try {
            // Simulate processing user activity (e.g., page view, click)
            log.info("Processing user activity: {}", message);
            log.info("Updating analytics dashboard with: {}", message);
        } catch (Exception e) {
            log.error("Failed to process activity: {}. Error: {}", message, e.getMessage());
            kafkaTemplate.send(DLT_TOPIC, record.key(), message); // Send to DLT on processing error
        }
    }
}