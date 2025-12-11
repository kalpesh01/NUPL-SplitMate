package com.splitmate.service.impl;

import com.splitmate.dto.expense_split.UpdateExpenseSplitDto;
import com.splitmate.dto.expense_split.ExpenseSplitInfoDto;
import com.splitmate.entity.ExpenseSplit;
import com.splitmate.dao.ExpenseSplitDao;
import com.splitmate.enums.PaymentStatus;
import com.splitmate.service.ExpenseSplitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseSplitServiceImpl implements ExpenseSplitService {

    private final ExpenseSplitDao expenseSplitDao;

    public ExpenseSplitServiceImpl(ExpenseSplitDao expenseSplitDao){
        this.expenseSplitDao = expenseSplitDao;
    }

    @Override
    public ExpenseSplitInfoDto create(ExpenseSplit expenseSplit) {
        return toResponse(expenseSplitDao.save(expenseSplit));
    }

    @Override
    public List<ExpenseSplitInfoDto> get(Long expenseId) {
        return expenseSplitDao.findAll().stream()
                .filter(s -> s.getExpense().getId().equals(expenseId))
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ExpenseSplitInfoDto update(Long expenseId, Long splitId, UpdateExpenseSplitDto req) {

        ExpenseSplit split = expenseSplitDao.findById(splitId)
                .orElseThrow(() -> new RuntimeException("Split not found"));

        split.setSplitAmount(req.getShareAmount());
        split.setPaymentStatus(PaymentStatus.valueOf(req.getPaymentStatus().toUpperCase()));
        expenseSplitDao.save(split);

        return toResponse(split);
    }

    @Override
    public void delete(Long expenseId, Long splitId) {
        expenseSplitDao.deleteById(splitId);
    }

    private ExpenseSplitInfoDto toResponse(ExpenseSplit split) {
        ExpenseSplitInfoDto res = new ExpenseSplitInfoDto();
        res.setId(split.getId());
        res.setExpenseId(split.getExpense().getId());
        res.setUserId(split.getOwnBy().getId());
        res.setShareAmount(split.getSplitAmount());
        res.setPaymentStatus(split.getPaymentStatus().toString().toUpperCase());
        return res;
    }
}

