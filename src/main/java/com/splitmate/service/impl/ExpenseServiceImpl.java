package com.splitmate.service.impl;

import com.splitmate.dao.*;
import com.splitmate.dto.request.ExpenseRequest;
import com.splitmate.dto.response.ExpenseResponse;
import com.splitmate.entity.Expense;
import com.splitmate.entity.ExpenseSplit;
import com.splitmate.entity.Group;
import com.splitmate.entity.User;
import com.splitmate.service.ExpenseService;
import com.splitmate.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private ExpenseSplitRepository expenseSplitRepository;

    @Override
    public ExpenseResponse createExpense(Long groupId, ExpenseRequest req) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        User paidBy = userRepository.findById(req.getPaidBy())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = new Expense();
        expense.setGroup(group);
        expense.setPaidBy(paidBy);
        expense.setAmount(req.getAmount());
        expense.setDescription(req.getDescription());
        expense.setExpenseDate(req.getExpenseDate());

        expenseRepository.save(expense);

        if (req.getUsersId() == null || req.getUsersId().isEmpty()) {
            throw new RuntimeException("Users list cannot be empty for splitting");
        }

        List<User> users =
                userRepository.findAllById(req.getUsersId());

        if (users.size() != req.getUsersId().size()) {
            throw new RuntimeException("Invalid userId in usersId list");
        }

        double splitAmount = req.getAmount() / users.size();

        for (User u : users) {
            ExpenseSplit split = new ExpenseSplit();
            split.setExpense(expense);
            split.setUser(u);
            split.setSplitAmount(splitAmount);
            split.setPaymentStatus("Pending");

            expenseSplitRepository.save(split);
        }
        return toResponse(expense);
    }

    @Override
    public List<ExpenseResponse> getExpensesByGroup(Long groupId) {
        return expenseRepository.findAll().stream()
                .filter(e -> e.getGroup().getId().equals(groupId))
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ExpenseResponse getExpenseById(Long expenseId) {
        Expense e = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        return toResponse(e);
    }

    @Override
    public ExpenseResponse updateExpense(Long expenseId, ExpenseRequest req) {
        Expense e = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        User paidBy = userRepository.findById(req.getPaidBy())
                .orElseThrow(() -> new RuntimeException("User not found"));

        e.setPaidBy(paidBy);
        e.setAmount(req.getAmount());
        e.setDescription(req.getDescription());
        e.setExpenseDate(req.getExpenseDate());

        expenseRepository.save(e);
        return toResponse(e);
    }

    @Override
    public void deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    private ExpenseResponse toResponse(Expense e) {
        ExpenseResponse res = new ExpenseResponse();
        res.setId(e.getId());
        res.setGroupId(e.getGroup().getId());
        res.setPaidBy(e.getPaidBy().getId());
        res.setAmount(e.getAmount());
        res.setDescription(e.getDescription());
        res.setExpenseDate(e.getExpenseDate());
        return res;
    }
}

