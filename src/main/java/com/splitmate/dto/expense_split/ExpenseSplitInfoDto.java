package com.splitmate.dto.expense_split;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseSplitInfoDto {
    private Long id;
    private Long expenseId;
    private Long userId;
    private Double shareAmount;
    private String paymentStatus;
}

