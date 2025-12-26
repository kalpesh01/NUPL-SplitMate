package com.splitmate.business.service;

import com.splitmate.db.dto.expense.CreateExpenseDTO;
import com.splitmate.db.dto.expense.ExpenseInfoDTO;
import com.splitmate.db.dto.expense.UpdateExpenseDTO;

import java.util.List;

public interface ExpenseService {

    ExpenseInfoDTO create(final Long groupId, final CreateExpenseDTO request);

    List<ExpenseInfoDTO> getByGroup(final Long groupId);

    ExpenseInfoDTO get(final Long expenseId);

    ExpenseInfoDTO update(final Long expenseId, UpdateExpenseDTO request);

    void delete(final Long expenseId);
}


