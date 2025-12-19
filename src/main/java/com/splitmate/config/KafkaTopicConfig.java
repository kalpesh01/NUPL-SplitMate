package com.splitmate.config;

import com.splitmate.kafka.topics.KafkaTopics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic expenseTopic() {
        return TopicBuilder.name(KafkaTopics.EXPENSE_SPLIT_NOTIFICATION)
                .partitions(2)
                .replicas(1)
                .build();
    }
}

