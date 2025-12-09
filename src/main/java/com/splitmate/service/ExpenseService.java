package com.splitmate.service;

import com.splitmate.dto.request.ExpenseRequest;
import com.splitmate.dto.response.ExpenseResponse;
import com.splitmate.entity.Expense;

import java.util.List;

public interface ExpenseService {

    ExpenseResponse createExpense(Long groupId, ExpenseRequest request);

    List<ExpenseResponse> getExpensesByGroup(Long groupId);

    ExpenseResponse getExpenseById(Long expenseId);

    ExpenseResponse updateExpense(Long expenseId, ExpenseRequest request);

    void deleteExpense(Long expenseId);
}


