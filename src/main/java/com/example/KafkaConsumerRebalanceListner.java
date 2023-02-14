package com.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;

@Slf4j
public class KafkaConsumerRebalanceListner implements ConsumerRebalanceListener {
    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> collection) {
        log.info("inside revoked partition");
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> collection) {
        log.info("inside assign partition");
    }
}
