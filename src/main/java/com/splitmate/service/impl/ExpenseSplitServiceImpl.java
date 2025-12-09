package com.splitmate.service.impl;

import com.splitmate.dao.ExpenseRepository;
import com.splitmate.dto.request.UpdateSplitRequest;
import com.splitmate.dto.response.SplitResponse;
import com.splitmate.entity.ExpenseSplit;
import com.splitmate.dao.ExpenseSplitRepository;
import com.splitmate.service.ExpenseSplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseSplitServiceImpl implements ExpenseSplitService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private ExpenseSplitRepository splitRepository;

    @Override
    public List<SplitResponse> getSplits(Long expenseId) {
        return splitRepository.findAll().stream()
                .filter(s -> s.getExpense().getId().equals(expenseId))
                .map(this::toResponse)
                .toList();
    }

    @Override
    public SplitResponse updateSplit(Long expenseId, Long splitId, UpdateSplitRequest req) {

        ExpenseSplit split = splitRepository.findById(splitId)
                .orElseThrow(() -> new RuntimeException("Split not found"));

        split.setSplitAmount(req.getShareAmount());
        split.setPaymentStatus(req.getPaymentStatus());
        splitRepository.save(split);

        return toResponse(split);
    }

    @Override
    public void deleteSplit(Long expenseId, Long splitId) {
        splitRepository.deleteById(splitId);
    }

    private SplitResponse toResponse(ExpenseSplit split) {
        SplitResponse res = new SplitResponse();
        res.setId(split.getId());
        res.setExpenseId(split.getExpense().getId());
        res.setUserId(split.getUser().getId());
        res.setShareAmount(split.getSplitAmount());
        res.setPaymentStatus(split.getPaymentStatus());
        return res;
    }
}

