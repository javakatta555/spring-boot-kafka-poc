package com.example.costraints;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Service;

@Service
public class KafkaCondition implements Condition {
    public KafkaCondition() {
    }

    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return (Boolean) Boolean.TRUE.equals(context.getEnvironment().getProperty("kafka.enabled", Boolean.class));
    }
}
