package com.splitmate.buissness.service.impl;

import com.splitmate.buissness.service.ExpenseService;
import com.splitmate.buissness.service.ExpenseSplitService;
import com.splitmate.buissness.service.GroupService;
import com.splitmate.buissness.service.UserService;
import com.splitmate.db.dao.ExpenseDao;
import com.splitmate.db.dto.expense.CreateExpenseDto;
import com.splitmate.db.dto.expense.ExpenseInfoDto;
import com.splitmate.db.dto.expense.UpdateExpenseDto;
import com.splitmate.db.entity.ExpenseEntity;
import com.splitmate.db.entity.ExpenseSplitEntity;
import com.splitmate.db.entity.GroupEntity;
import com.splitmate.db.entity.UserEntity;
import com.splitmate.enums.ErrorCodes;
import com.splitmate.enums.PaymentStatus;
import com.splitmate.exception.error.BadRequestException;
import com.splitmate.exception.error.ResourceNotFoundException;
import com.splitmate.kafka.event.ExpenseSplitNotificationDto;
import com.splitmate.kafka.publish.ExpenseSplitNotificationPublisher;
import com.splitmate.mapper.ExpenseMapper;
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

        List<UserEntity> users =
                userService.findAllByIdIn(createExpenseDto.getUsersId());

        if (users.isEmpty()) {
            throw new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage());
        }

        GroupEntity groupEntity = groupService.findById(groupId);
        UserEntity paidBy = userService.findById(createExpenseDto.getPaidBy());

        final ExpenseEntity expenseEntity = expenseMapper.dtoToEntity(createExpenseDto, groupEntity, paidBy);
        expenseDao.save(expenseEntity);

        double splitAmount = createExpenseDto.getAmount() / users.size();

        for (UserEntity u : users) {
            ExpenseSplitEntity split = new ExpenseSplitEntity();
            split.setExpenseEntity(expenseEntity);
            split.setOwnBy(u);
            split.setSplitAmount(splitAmount);
            split.setPaymentStatus(PaymentStatus.PENDING);

            expenseSplitService.create(split);
            ExpenseSplitNotificationDto notificationDto = new ExpenseSplitNotificationDto(expenseEntity.getId(), expenseEntity.getGroupEntity().getId(),u.getId(),u.getEmail(), expenseEntity.getAmount(),split.getSplitAmount());
            expenseSplitNotificationPublisher.publish(notificationDto);
        }
        return expenseMapper.entityToDto(expenseEntity);
    }

    @Override
    public List<ExpenseInfoDto> getByGroup(final Long groupId) {
        return expenseDao.findAll().stream()
                .filter(e -> e.getGroupEntity().getId().equals(groupId))
                .map(expenseMapper::entityToDto)
                .toList();
    }

    @Override
    public ExpenseInfoDto get(final Long expenseId) {
        ExpenseEntity expenseEntity = expenseDao.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage()));
        return expenseMapper.entityToDto(expenseEntity);
    }

    @Override
    public ExpenseInfoDto update(final Long expenseId, UpdateExpenseDto req) {
        ExpenseEntity expenseEntity = expenseDao.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.EXPENSE_NOT_FOUND.getMessage()));

        UserEntity paidBy = userService.findById(req.getPaidBy());
        expenseMapper.updateDtoToEntity(req, paidBy, expenseEntity);
        expenseDao.save(expenseEntity);

        return expenseMapper.entityToDto(expenseEntity);
    }

    @Override
    public void delete(final Long expenseId) {
        expenseDao.deleteById(expenseId);
    }
}

