package com.splitmate.controller;

import com.splitmate.dto.expense.CreateExpenseDto;
import com.splitmate.dto.expense.ExpenseInfoDto;
import com.splitmate.dto.expense.UpdateExpenseDto;
import com.splitmate.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@AllArgsConstructor
public class ExpenseController {

    private final ExpenseService service;

    @PostMapping("/groups/{groupId}")
    public ExpenseInfoDto create(
            @PathVariable final Long groupId,
            @RequestBody final CreateExpenseDto request) {
        return service.create(groupId, request);
    }

    @GetMapping("/groups/{groupId}")
    public List<ExpenseInfoDto> getAll(
            @PathVariable final Long groupId) {
        return service.getByGroup(groupId);
    }

    @GetMapping("/{expenseId}")
    public ExpenseInfoDto getById(
            @PathVariable final Long expenseId) {
        return service.get(expenseId);
    }

    @PutMapping("/{expenseId}")
    public ExpenseInfoDto update(
            @PathVariable final Long expenseId,
            @RequestBody final UpdateExpenseDto request) {
        return service.update(expenseId, request);
    }

    @DeleteMapping("/{expenseId}")
    public void delete(@PathVariable final Long expenseId) {
        service.delete(expenseId);
    }
}

