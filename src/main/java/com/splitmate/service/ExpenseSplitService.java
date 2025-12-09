package com.splitmate.service;

import com.splitmate.dto.request.UpdateSplitRequest;
import com.splitmate.dto.response.SplitResponse;
import com.splitmate.entity.ExpenseSplit;

import java.util.List;

public interface ExpenseSplitService {

    List<SplitResponse> getSplits(Long expenseId);

    SplitResponse updateSplit(Long expenseId, Long splitId, UpdateSplitRequest req);

    void deleteSplit(Long expenseId, Long splitId);
}


