package com.splitmate.buissness.service.impl;

import com.splitmate.db.dto.expense_split.UpdateExpenseSplitDto;
import com.splitmate.db.dto.expense_split.ExpenseSplitInfoDto;
import com.splitmate.db.entity.ExpenseSplitEntity;
import com.splitmate.db.dao.ExpenseSplitDao;
import com.splitmate.mapper.ExpenseSplitMapper;
import com.splitmate.buissness.service.ExpenseSplitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseSplitServiceImpl implements ExpenseSplitService {

    private final ExpenseSplitDao expenseSplitDao;
    private final ExpenseSplitMapper expenseSplitMapper;

    @Override
    public ExpenseSplitInfoDto create(ExpenseSplitEntity expenseSplitEntity) {
        return expenseSplitMapper.entityToDto(expenseSplitDao.save(expenseSplitEntity));
    }

    @Override
    public List<ExpenseSplitInfoDto> get(final Long expenseId) {
        List<ExpenseSplitInfoDto> list = expenseSplitDao.findAll().stream()
                .filter(s -> s.getExpenseEntity().getId().equals(expenseId))
                .map(expenseSplitMapper::entityToDto)
                .toList();

        return list;
    }

    @Override
    public ExpenseSplitInfoDto update(final Long expenseId, final Long splitId, final UpdateExpenseSplitDto updateExpenseSplitDto) {

        ExpenseSplitEntity split = expenseSplitDao.findById(splitId)
                .orElseThrow(() -> new RuntimeException("Split not found"));

        expenseSplitMapper.updateDtoToEntity(updateExpenseSplitDto, split);
        expenseSplitDao.save(split);

        return expenseSplitMapper.entityToDto(split);
    }

    @Override
    public void delete(final Long expenseId, final Long splitId) {
        expenseSplitDao.deleteById(splitId);
    }
}

