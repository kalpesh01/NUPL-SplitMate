package com.splitmate.service;

import com.splitmate.dto.expense.CreateExpenseDto;
import com.splitmate.dto.expense.ExpenseInfoDto;
import com.splitmate.dto.expense.UpdateExpenseDto;

import java.util.List;

public interface ExpenseService {

    ExpenseInfoDto create(Long groupId, CreateExpenseDto request);

    List<ExpenseInfoDto> getByGroup(Long groupId);

    ExpenseInfoDto get(Long expenseId);

    ExpenseInfoDto update(Long expenseId, UpdateExpenseDto request);

    void delete(Long expenseId);
}


