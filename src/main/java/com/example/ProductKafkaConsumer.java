package com.example;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class ProductKafkaConsumer {

    @KafkaListener(id = "foo", topics = "${product.topic.name}")
    public void listen(String data, final Consumer consumer, Acknowledgment acknowledgment) {
        System.out.println("Message is :"+data);
        acknowledgment.acknowledge();
    }
}
