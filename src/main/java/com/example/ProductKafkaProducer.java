package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductKafkaProducer {

    @Autowired
    private KafkaProducer<String,String> kafkaProducer;

    public void publish_without_key(String topic, String message) {
        log.info("[publish_without_key] kafkaProducer {}", kafkaProducer);
        kafkaProducer.send(topic, message);
    }

    public void publish_with_key(String topic, String key, String message) {
        kafkaProducer.send(topic, key, message);
    }
}
