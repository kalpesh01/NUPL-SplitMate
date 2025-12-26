package com.splitmate.kafka.publish;

import com.splitmate.kafka.event.ExpenseSplitNotificationDTO;
import com.splitmate.kafka.topics.KafkaTopics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExpenseSplitNotificationPublisher {

    private final KafkaTemplate<String, ExpenseSplitNotificationDTO> kafkaTemplate;

    public ExpenseSplitNotificationPublisher(
            KafkaTemplate<String, ExpenseSplitNotificationDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(ExpenseSplitNotificationDTO notificationDto) {
        kafkaTemplate.send(
                KafkaTopics.EXPENSE_SPLIT_NOTIFICATION,
                notificationDto.getExpenseId().toString(),
                notificationDto
        );
    }
}
