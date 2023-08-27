package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer<K,V> {
    @Autowired
    private KafkaTemplate<K, V> kafkaTemplate;
    @Value("${kafka.enabled}")
    private boolean isKafkaEnabled;

    public void send(String topic, K key, V value) {
        if (this.isKafkaEnabled) {
            this.kafkaTemplate.send(topic, key, value);
        }
    }

    public void send(String topic, Integer partition, K key, V value) {
        if (this.isKafkaEnabled) {
            this.kafkaTemplate.send(topic, partition, key, value);
        }
    }

    public void send(String topic, V value) {
        if (this.isKafkaEnabled) {
            this.kafkaTemplate.send(topic, value);
        }
    }
}
