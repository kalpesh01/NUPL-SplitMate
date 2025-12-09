package com.splitmate.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SplitResponse {
    private Long id;
    private Long expenseId;
    private Long userId;
    private Double shareAmount;
    private String paymentStatus;
}

