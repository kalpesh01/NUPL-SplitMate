package com.splitmate.controller;

import com.splitmate.dto.request.ExpenseRequest;
import com.splitmate.dto.response.ExpenseResponse;
import com.splitmate.entity.Expense;
import com.splitmate.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @PostMapping("/groups/{groupId}")
    public ResponseEntity<ExpenseResponse> create(
            @PathVariable Long groupId,
            @RequestBody ExpenseRequest request) {
        return ResponseEntity.ok(service.createExpense(groupId, request));
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<List<ExpenseResponse>> getAll(
            @PathVariable Long groupId) {
        return ResponseEntity.ok(service.getExpensesByGroup(groupId));
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> getById(
            @PathVariable Long expenseId) {
        return ResponseEntity.ok(service.getExpenseById(expenseId));
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> update(
            @PathVariable Long expenseId,
            @RequestBody ExpenseRequest request) {
        return ResponseEntity.ok(service.updateExpense(expenseId, request));
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> delete(@PathVariable Long expenseId) {
        service.deleteExpense(expenseId);
        return ResponseEntity.noContent().build();
    }
}

