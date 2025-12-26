package com.splitmate.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseSplitNotificationDTO {

    private Long expenseId;
    private Long groupId;
    private Long userId;
    private String email;
    private Double totalAmount;
    private Double splitAmountPerUser;
}

