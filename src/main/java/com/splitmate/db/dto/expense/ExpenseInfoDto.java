package com.splitmate.db.dto.expense;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExpenseInfoDto {
    private Long id;
    private Long groupId;
    private Long paidBy;
    private Double amount;
    private String description;
    private LocalDate expenseDate;
}

