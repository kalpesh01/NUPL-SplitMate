package com.splitmate.service.impl;

import com.splitmate.dao.*;
import com.splitmate.dto.expense.CreateExpenseDto;
import com.splitmate.dto.expense.ExpenseInfoDto;
import com.splitmate.dto.expense.UpdateExpenseDto;
import com.splitmate.entity.Expense;
import com.splitmate.entity.ExpenseSplit;
import com.splitmate.entity.Group;
import com.splitmate.entity.User;
import com.splitmate.enums.PaymentStatus;
import com.splitmate.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final UserService userService;

    private final ExpenseDao expenseDao;

    private final ExpenseSplitService expenseSplitService;

    private final GroupService groupService;

    public ExpenseServiceImpl(UserService userService, ExpenseDao expenseDao, ExpenseSplitService expenseSplitService,
                              GroupService groupService){
        this.groupService = groupService;
        this.userService = userService;
        this.expenseDao = expenseDao;
        this.expenseSplitService = expenseSplitService;
    }

    @Override
    public ExpenseInfoDto create(Long groupId, CreateExpenseDto req) {

        Group group = groupService.findById(groupId);

        User paidBy = userService.findById(req.getPaidBy());

        Expense expense = new Expense();
        expense.setGroup(group);
        expense.setPaidOutBy(paidBy);
        expense.setAmount(req.getAmount());
        expense.setDescription(req.getDescription());
        expense.setExpenseDate(req.getExpenseDate());

        expenseDao.save(expense);

        if (req.getUsersId() == null || req.getUsersId().isEmpty()) {
            throw new RuntimeException("Users list cannot be empty for splitting");
        }

        List<User> users =
                userService.findAll();

        if (users.size() != req.getUsersId().size()) {
            throw new RuntimeException("Invalid userId in usersId list");
        }

        double splitAmount = req.getAmount() / users.size();

        for (User u : users) {
            ExpenseSplit split = new ExpenseSplit();
            split.setExpense(expense);
            split.setOwnBy(u);
            split.setSplitAmount(splitAmount);
            split.setPaymentStatus(PaymentStatus.PENDING);

            expenseSplitService.create(split);
        }
        return toResponse(expense);
    }

    @Override
    public List<ExpenseInfoDto> getByGroup(Long groupId) {
        return expenseDao.findAll().stream()
                .filter(e -> e.getGroup().getId().equals(groupId))
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ExpenseInfoDto get(Long expenseId) {
        Expense e = expenseDao.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        return toResponse(e);
    }

    @Override
    public ExpenseInfoDto update(Long expenseId, UpdateExpenseDto req) {
        Expense e = expenseDao.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        User paidBy = userService.findById(req.getPaidBy());

        e.setPaidOutBy(paidBy);
        e.setAmount(req.getAmount());
        e.setDescription(req.getDescription());
        e.setExpenseDate(req.getExpenseDate());

        expenseDao.save(e);
        return toResponse(e);
    }

    @Override
    public void delete(Long expenseId) {
        expenseDao.deleteById(expenseId);
    }

    private ExpenseInfoDto toResponse(Expense e) {
        ExpenseInfoDto res = new ExpenseInfoDto();
        res.setId(e.getId());
        res.setGroupId(e.getGroup().getId());
        res.setPaidBy(e.getPaidOutBy().getId());
        res.setAmount(e.getAmount());
        res.setDescription(e.getDescription());
        res.setExpenseDate(e.getExpenseDate());
        return res;
    }
}

