package com.splitmate.dto.expense;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class UpdateExpenseDto {
    private Long paidBy;
    private Double amount;
    private String description;
    private LocalDate expenseDate;
    private List<Long> usersId;
}
