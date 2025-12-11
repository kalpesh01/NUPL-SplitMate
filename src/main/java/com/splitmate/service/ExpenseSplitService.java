package com.splitmate.service;

import com.splitmate.dto.expense_split.UpdateExpenseSplitDto;
import com.splitmate.dto.expense_split.ExpenseSplitInfoDto;
import com.splitmate.entity.ExpenseSplit;

import java.util.List;

public interface ExpenseSplitService {

    ExpenseSplitInfoDto create(ExpenseSplit expenseSplit);

    List<ExpenseSplitInfoDto> get(Long expenseId);

    ExpenseSplitInfoDto update(Long expenseId, Long splitId, UpdateExpenseSplitDto req);

    void delete(Long expenseId, Long splitId);
}


