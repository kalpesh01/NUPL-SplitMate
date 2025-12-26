package com.splitmate.controller;

import com.splitmate.db.dto.expense.CreateExpenseDTO;
import com.splitmate.db.dto.expense.ExpenseInfoDTO;
import com.splitmate.db.dto.expense.UpdateExpenseDTO;
import com.splitmate.business.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@AllArgsConstructor
public class ExpenseController {

    private final ExpenseService service;

    @PostMapping("/groups/{groupId}")
    public ExpenseInfoDTO create(
            @PathVariable final Long groupId,
            @RequestBody final CreateExpenseDTO request) {
        return service.create(groupId, request);
    }

    @GetMapping("/groups/{groupId}")
    public List<ExpenseInfoDTO> getAll(
            @PathVariable final Long groupId) {
        return service.getByGroup(groupId);
    }

    @GetMapping("/{expenseId}")
    public ExpenseInfoDTO getById(
            @PathVariable final Long expenseId) {
        return service.get(expenseId);
    }

    @PutMapping("/{expenseId}")
    public ExpenseInfoDTO update(
            @PathVariable final Long expenseId,
            @RequestBody final UpdateExpenseDTO request) {
        return service.update(expenseId, request);
    }

    @DeleteMapping("/{expenseId}")
    public void delete(@PathVariable final Long expenseId) {
        service.delete(expenseId);
    }
}

