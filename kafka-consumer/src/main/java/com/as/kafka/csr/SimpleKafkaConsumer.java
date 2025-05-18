package com.as.kafka.csr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleKafkaConsumer.class);

    @KafkaListener(topics = "user-topic", groupId = "group2")
    public void consumeMessage(String message) {
        LOGGER.info("Consumed message: {}", message);
        // You can add any processing logic here
    }
}