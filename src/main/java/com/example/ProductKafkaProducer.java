package com.example;

import com.example.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductKafkaProducer {

    @Autowired
    private KafkaProducer<String,String> kafkaProducer;

    @Value("${product.topic.name}")
    private String topicName;

    public void publish_without_key(Product product) {
        String message = JsonUtils.asJsonString(product);
        log.info("[publish_without_key] kafkaProducer {}", message);
        kafkaProducer.send(topicName, message);
    }

    public void publish_with_key(String topic, String key, Product product) {
        String message = JsonUtils.asJsonString(product);
        kafkaProducer.send(topicName, key, message);
    }
}
