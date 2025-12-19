package com.splitmate.controller;

import com.splitmate.dto.expense_split.UpdateExpenseSplitDto;
import com.splitmate.dto.expense_split.ExpenseSplitInfoDto;
import com.splitmate.service.ExpenseSplitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses/{expenseId}/splits")
@AllArgsConstructor
public class ExpenseSplitController {

    private final ExpenseSplitService expenseSplitService;

    @GetMapping
    public List<ExpenseSplitInfoDto> getAll(@PathVariable final Long expenseId) {
        return expenseSplitService.get(expenseId);
    }

    @PutMapping("/{splitId}")
    public ExpenseSplitInfoDto update(
            @PathVariable final Long expenseId,
            @PathVariable final Long splitId,
            @RequestBody final UpdateExpenseSplitDto req) {

        return expenseSplitService.update(expenseId, splitId, req);
    }

    @DeleteMapping("/{splitId}")
    public void delete(
            @PathVariable final Long expenseId,
            @PathVariable final Long splitId) {

        expenseSplitService.delete(expenseId, splitId);
    }
}

