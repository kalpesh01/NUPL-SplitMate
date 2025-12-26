package com.splitmate.business.service;

import com.splitmate.db.dto.expense_split.UpdateExpenseSplitDTO;
import com.splitmate.db.dto.expense_split.ExpenseSplitInfoDTO;
import com.splitmate.db.entity.ExpenseSplitEntity;

import java.util.List;

public interface ExpenseSplitService {

    ExpenseSplitInfoDTO create(ExpenseSplitEntity expenseSplitEntity);

    List<ExpenseSplitInfoDTO> get(final Long expenseId);

    ExpenseSplitInfoDTO update(final Long expenseId, final Long splitId, final UpdateExpenseSplitDTO req);

    void delete(final Long expenseId, final Long splitId);
}


