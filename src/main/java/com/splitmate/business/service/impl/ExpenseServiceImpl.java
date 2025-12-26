package com.splitmate.business.service.impl;

import com.splitmate.business.service.ExpenseService;
import com.splitmate.business.service.ExpenseSplitService;
import com.splitmate.business.service.GroupService;
import com.splitmate.business.service.UserService;
import com.splitmate.db.dao.ExpenseDAO;
import com.splitmate.db.dto.expense.CreateExpenseDTO;
import com.splitmate.db.dto.expense.ExpenseInfoDTO;
import com.splitmate.db.dto.expense.UpdateExpenseDTO;
import com.splitmate.db.entity.ExpenseEntity;
import com.splitmate.db.entity.ExpenseSplitEntity;
import com.splitmate.db.entity.GroupEntity;
import com.splitmate.db.entity.UserEntity;
import com.splitmate.enums.ErrorCodes;
import com.splitmate.enums.PaymentStatus;
import com.splitmate.exception.error.BadRequestException;
import com.splitmate.exception.error.ResourceNotFoundException;
import com.splitmate.kafka.event.ExpenseSplitNotificationDTO;
import com.splitmate.kafka.publish.ExpenseSplitNotificationPublisher;
import com.splitmate.mapper.ExpenseMapper;
import com.splitmate.mapper.ExpenseSplitMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService
{

    private final UserService userService;

    private final ExpenseDAO expenseDao;

    private final ExpenseSplitService expenseSplitService;

    private final GroupService groupService;

    private final ExpenseSplitNotificationPublisher expenseSplitNotificationPublisher;

    private final ExpenseMapper expenseMapper;

    private final ExpenseSplitMapper expenseSplitMapper;

    @Override
    public ExpenseInfoDTO create(final Long groupId, final CreateExpenseDTO createExpenseDto)
    {

        if (createExpenseDto.getUsersId() == null || createExpenseDto.getUsersId().isEmpty())
        {
            throw new BadRequestException(ErrorCodes.INVALID_USER_LIST.getMessage());
        }

        List<UserEntity> users = userService.findAllByIdIn(createExpenseDto.getUsersId());

        if (users.isEmpty())
        {
            throw new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage());
        }

        GroupEntity groupEntity = groupService.findById(groupId);
        UserEntity paidBy = userService.findById(createExpenseDto.getPaidBy());

        final ExpenseEntity expenseEntity = expenseMapper.dtoToEntity(createExpenseDto, groupEntity, paidBy);
        expenseDao.save(expenseEntity);

        double splitAmount = createExpenseDto.getAmount() / users.size();

        for (UserEntity u : users)
        {
            ExpenseSplitEntity expenseSplitEntity = expenseSplitMapper.toExpenseSplitEntity(expenseEntity, u, splitAmount, PaymentStatus.PENDING);
            expenseSplitService.create(expenseSplitEntity);
            ExpenseSplitNotificationDTO notificationDto = new ExpenseSplitNotificationDTO(expenseEntity.getId(), expenseEntity.getGroupEntity().getId(), u.getId(), u.getEmail(),
                    expenseEntity.getAmount(), expenseSplitEntity.getSplitAmount());
            expenseSplitNotificationPublisher.publish(notificationDto);
        }
        return expenseMapper.entityToDto(expenseEntity);
    }

    @Override
    public List<ExpenseInfoDTO> getByGroup(final Long groupId)
    {
        return expenseDao.findAll().stream().filter(e -> e.getGroupEntity().getId().equals(groupId)).map(expenseMapper::entityToDto).toList();
    }

    @Override
    public ExpenseInfoDTO get(final Long expenseId)
    {
        ExpenseEntity expenseEntity = expenseDao.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage()));
        return expenseMapper.entityToDto(expenseEntity);
    }

    @Override
    public ExpenseInfoDTO update(final Long expenseId, UpdateExpenseDTO req)
    {
        ExpenseEntity expenseEntity = expenseDao.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.EXPENSE_NOT_FOUND.getMessage()));

        UserEntity paidBy = userService.findById(req.getPaidBy());
        expenseMapper.updateDtoToEntity(req, paidBy, expenseEntity);
        expenseDao.save(expenseEntity);

        return expenseMapper.entityToDto(expenseEntity);
    }

    @Override
    public void delete(final Long expenseId)
    {
        expenseDao.deleteById(expenseId);
    }
}

