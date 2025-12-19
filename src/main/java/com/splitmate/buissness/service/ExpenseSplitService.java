package com.splitmate.buissness.service;

import com.splitmate.db.dto.expense_split.UpdateExpenseSplitDto;
import com.splitmate.db.dto.expense_split.ExpenseSplitInfoDto;
import com.splitmate.db.entity.ExpenseSplitEntity;

import java.util.List;

public interface ExpenseSplitService {

    ExpenseSplitInfoDto create(ExpenseSplitEntity expenseSplitEntity);

    List<ExpenseSplitInfoDto> get(final Long expenseId);

    ExpenseSplitInfoDto update(final Long expenseId, final Long splitId, final UpdateExpenseSplitDto req);

    void delete(final Long expenseId, final Long splitId);
}


