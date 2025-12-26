package com.splitmate.db.dto.expense_split;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateExpenseSplitDTO
{
    private Double shareAmount;
    private String paymentStatus;
}

