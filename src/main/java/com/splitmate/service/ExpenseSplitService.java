package com.splitmate.service;

import com.splitmate.dto.expense_split.UpdateExpenseSplitDto;
import com.splitmate.dto.expense_split.ExpenseSplitInfoDto;
import com.splitmate.entity.ExpenseSplit;

import java.util.List;

public interface ExpenseSplitService {
    
    ExpenseSplitInfoDto create(ExpenseSplit expenseSplit);

    List<ExpenseSplitInfoDto> get(final Long expenseId);

    ExpenseSplitInfoDto update(final Long expenseId, final Long splitId, final UpdateExpenseSplitDto req);

    void delete(final Long expenseId, final Long splitId);
}


