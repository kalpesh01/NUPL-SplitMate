package com.splitmate.controller;

import com.splitmate.dto.request.UpdateSplitRequest;
import com.splitmate.dto.response.SplitResponse;
import com.splitmate.entity.ExpenseSplit;
import com.splitmate.service.ExpenseSplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses/{expenseId}/splits")
public class ExpenseSplitController {

    @Autowired
    private ExpenseSplitService service;

    @GetMapping
    public ResponseEntity<List<SplitResponse>> getAll(@PathVariable Long expenseId) {
        return ResponseEntity.ok(service.getSplits(expenseId));
    }

    @PutMapping("/{splitId}")
    public ResponseEntity<SplitResponse> update(
            @PathVariable Long expenseId,
            @PathVariable Long splitId,
            @RequestBody UpdateSplitRequest req) {

        return ResponseEntity.ok(service.updateSplit(expenseId, splitId, req));
    }

    @DeleteMapping("/{splitId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long expenseId,
            @PathVariable Long splitId) {

        service.deleteSplit(expenseId, splitId);
        return ResponseEntity.noContent().build();
    }
}

