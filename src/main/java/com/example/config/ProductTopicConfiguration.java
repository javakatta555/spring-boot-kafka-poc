package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ProductTopicConfiguration {

    @Value(value = "${product.consumer.topic}")
    private String topicName;

    @Value(value = "${product.partitions}")
    private int partitions;

    @Value(value = "${product.replicas}")
    private int replicas;

    @Bean
    public NewTopic productTopics(){
        return TopicBuilder.name(topicName)
                .partitions(partitions)
                .replicas(replicas)
                .build();
    }
}
