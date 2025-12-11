package com.splitmate.controller;

import com.splitmate.dto.expense_split.UpdateExpenseSplitDto;
import com.splitmate.dto.expense_split.ExpenseSplitInfoDto;
import com.splitmate.service.ExpenseSplitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses/{expenseId}/splits")
public class ExpenseSplitController {

    private final ExpenseSplitService expenseSplitService;

    public ExpenseSplitController(ExpenseSplitService expenseSplitService) {
        this.expenseSplitService = expenseSplitService;
    }
    @GetMapping
    public List<ExpenseSplitInfoDto> getAll(@PathVariable Long expenseId) {
        return expenseSplitService.get(expenseId);
    }

    @PutMapping("/{splitId}")
    public ExpenseSplitInfoDto update(
            @PathVariable Long expenseId,
            @PathVariable Long splitId,
            @RequestBody UpdateExpenseSplitDto req) {

        return expenseSplitService.update(expenseId, splitId, req);
    }

    @DeleteMapping("/{splitId}")
    public void delete(
            @PathVariable Long expenseId,
            @PathVariable Long splitId) {

        expenseSplitService.delete(expenseId, splitId);
    }
}

