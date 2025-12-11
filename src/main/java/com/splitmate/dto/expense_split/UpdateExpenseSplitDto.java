package com.splitmate.dto.expense_split;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateExpenseSplitDto {
    private Double shareAmount;
    private String paymentStatus;
}

