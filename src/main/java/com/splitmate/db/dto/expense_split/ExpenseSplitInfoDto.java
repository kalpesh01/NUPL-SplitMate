package com.splitmate.db.dto.expense_split;

import com.splitmate.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseSplitInfoDto {
    private Long id;

    private double splitAmount;

    private PaymentStatus paymentStatus;

    private Long expenseId;

    private Long userId;
}

