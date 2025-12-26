package com.splitmate.buissness.service;

import com.splitmate.db.dto.expense.CreateExpenseDto;
import com.splitmate.db.dto.expense.ExpenseInfoDto;
import com.splitmate.db.dto.expense.UpdateExpenseDto;

import java.util.List;

public interface ExpenseService {

    ExpenseInfoDto create(final Long groupId, final CreateExpenseDto request);

    List<ExpenseInfoDto> getByGroup(final Long groupId);

    ExpenseInfoDto get(final Long expenseId);

    ExpenseInfoDto update(final Long expenseId, UpdateExpenseDto request);

    void delete(final Long expenseId);
}


