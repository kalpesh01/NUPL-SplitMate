package com.splitmate.kafka.listener;

import com.splitmate.kafka.event.ExpenseSplitNotificationDto;
import com.splitmate.kafka.topics.KafkaTopics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ExpenseSplitNotificationListener {

    @KafkaListener(
            topics = KafkaTopics.EXPENSE_SPLIT_NOTIFICATION,
            groupId = "splitmate-group"
    )
    public void listen(ExpenseSplitNotificationDto event) {

        System.out.println("Message received for "+ event.getEmail());
    }
}

