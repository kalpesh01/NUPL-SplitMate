package com.splitmate.kafka.publish;

import com.splitmate.kafka.event.ExpenseSplitNotificationDto;
import com.splitmate.kafka.topics.KafkaTopics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExpenseSplitNotificationPublisher {

    private final KafkaTemplate<String, ExpenseSplitNotificationDto> kafkaTemplate;

    public ExpenseSplitNotificationPublisher(
            KafkaTemplate<String, ExpenseSplitNotificationDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(ExpenseSplitNotificationDto notificationDto) {
        kafkaTemplate.send(
                KafkaTopics.EXPENSE_SPLIT_NOTIFICATION,
                notificationDto.getExpenseId().toString(),
                notificationDto
        );
    }
}
