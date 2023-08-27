package com.example.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaAdminConfiguration {

    @Value("${kafka.connect.url}")
    private String kafkaUrl;
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        // Depending on you Kafka Cluster setup you need to configure
        // additional properties!
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);
        return new KafkaAdmin(configs);
    }
}
