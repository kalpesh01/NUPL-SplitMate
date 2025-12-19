package com.splitmate.service;

import com.splitmate.dto.expense.CreateExpenseDto;
import com.splitmate.dto.expense.ExpenseInfoDto;
import com.splitmate.dto.expense.UpdateExpenseDto;

import java.util.List;

public interface ExpenseService {

    ExpenseInfoDto create(final Long groupId, final CreateExpenseDto request);

    List<ExpenseInfoDto> getByGroup(final Long groupId);

    ExpenseInfoDto get(final Long expenseId);

    ExpenseInfoDto update(final Long expenseId, UpdateExpenseDto request);

    void delete(final Long expenseId);
}


