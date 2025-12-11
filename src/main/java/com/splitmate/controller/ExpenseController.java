package com.splitmate.controller;

import com.splitmate.dto.expense.CreateExpenseDto;
import com.splitmate.dto.expense.ExpenseInfoDto;
import com.splitmate.dto.expense.UpdateExpenseDto;
import com.splitmate.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping("/groups/{groupId}")
    public ExpenseInfoDto create(
            @PathVariable Long groupId,
            @RequestBody CreateExpenseDto request) {
        return service.create(groupId, request);
    }

    @GetMapping("/groups/{groupId}")
    public List<ExpenseInfoDto> getAll(
            @PathVariable Long groupId) {
        return service.getByGroup(groupId);
    }

    @GetMapping("/{expenseId}")
    public ExpenseInfoDto getById(
            @PathVariable Long expenseId) {
        return service.get(expenseId);
    }

    @PutMapping("/{expenseId}")
    public ExpenseInfoDto update(
            @PathVariable Long expenseId,
            @RequestBody UpdateExpenseDto request) {
        return service.update(expenseId, request);
    }

    @DeleteMapping("/{expenseId}")
    public void delete(@PathVariable Long expenseId) {
        service.delete(expenseId);
    }
}

