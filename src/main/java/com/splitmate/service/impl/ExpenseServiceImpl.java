package com.splitmate.service.impl;

import com.splitmate.dao.*;
import com.splitmate.dto.expense.CreateExpenseDto;
import com.splitmate.dto.expense.ExpenseInfoDto;
import com.splitmate.dto.expense.UpdateExpenseDto;
import com.splitmate.entity.Expense;
import com.splitmate.entity.ExpenseSplit;
import com.splitmate.entity.Group;
import com.splitmate.entity.User;
import com.splitmate.enums.ErrorCodes;
import com.splitmate.enums.PaymentStatus;
import com.splitmate.exception.error.BadRequestException;
import com.splitmate.exception.error.ResourceNotFoundException;
import com.splitmate.kafka.event.ExpenseSplitNotificationDto;
import com.splitmate.kafka.publish.ExpenseSplitNotificationPublisher;
import com.splitmate.mapper.ExpenseMapper;
import com.splitmate.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final UserService userService;

    private final ExpenseDao expenseDao;

    private final ExpenseSplitService expenseSplitService;

    private final GroupService groupService;

    private final ExpenseSplitNotificationPublisher expenseSplitNotificationPublisher;

    private final ExpenseMapper expenseMapper;

    @Override
    public ExpenseInfoDto create(final Long groupId, final CreateExpenseDto createExpenseDto) {

        if (createExpenseDto.getUsersId() == null || createExpenseDto.getUsersId().isEmpty()) {
            throw new BadRequestException(ErrorCodes.INVALID_USER_LIST.getMessage());
        }

        List<User> users =
                userService.findAllByIdIn(createExpenseDto.getUsersId());

        if (users.isEmpty()) {
            throw new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage());
        }

        Group group = groupService.findById(groupId);
        User paidBy = userService.findById(createExpenseDto.getPaidBy());

        final Expense expense = expenseMapper.dtoToEntity(createExpenseDto, group, paidBy);
        expenseDao.save(expense);

        double splitAmount = createExpenseDto.getAmount() / users.size();

        for (User u : users) {
            ExpenseSplit split = new ExpenseSplit();
            split.setExpense(expense);
            split.setOwnBy(u);
            split.setSplitAmount(splitAmount);
            split.setPaymentStatus(PaymentStatus.PENDING);

            expenseSplitService.create(split);
            ExpenseSplitNotificationDto notificationDto = new ExpenseSplitNotificationDto(expense.getId(),expense.getGroup().getId(),u.getId(),u.getEmail(),expense.getAmount(),split.getSplitAmount());
            expenseSplitNotificationPublisher.publish(notificationDto);
        }
        return expenseMapper.entityToDto(expense);
    }

    @Override
    public List<ExpenseInfoDto> getByGroup(final Long groupId) {
        return expenseDao.findAll().stream()
                .filter(e -> e.getGroup().getId().equals(groupId))
                .map(expenseMapper::entityToDto)
                .toList();
    }

    @Override
    public ExpenseInfoDto get(final Long expenseId) {
        Expense expense = expenseDao.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage()));
        return expenseMapper.entityToDto(expense);
    }

    @Override
    public ExpenseInfoDto update(final Long expenseId, UpdateExpenseDto req) {
        Expense expense = expenseDao.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.EXPENSE_NOT_FOUND.getMessage()));

        User paidBy = userService.findById(req.getPaidBy());
        expenseMapper.updateDtoToEntity(req, paidBy, expense);
        expenseDao.save(expense);

        return expenseMapper.entityToDto(expense);
    }

    @Override
    public void delete(final Long expenseId) {
        expenseDao.deleteById(expenseId);
    }
}

