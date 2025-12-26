package com.splitmate.controller;

import com.splitmate.db.dto.expense_split.ExpenseSplitInfoDTO;
import com.splitmate.db.dto.expense_split.UpdateExpenseSplitDTO;
import com.splitmate.business.service.ExpenseSplitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses/{expenseId}/splits")
@AllArgsConstructor
public class ExpenseSplitController {

    private final ExpenseSplitService expenseSplitService;

    @GetMapping
    public List<ExpenseSplitInfoDTO> getAll(@PathVariable final Long expenseId) {
        return expenseSplitService.get(expenseId);
    }

    @PutMapping("/{splitId}")
    public ExpenseSplitInfoDTO update(
            @PathVariable final Long expenseId,
            @PathVariable final Long splitId,
            @RequestBody final UpdateExpenseSplitDTO req) {

        return expenseSplitService.update(expenseId, splitId, req);
    }

    @DeleteMapping("/{splitId}")
    public void delete(
            @PathVariable final Long expenseId,
            @PathVariable final Long splitId) {

        expenseSplitService.delete(expenseId, splitId);
    }
}

