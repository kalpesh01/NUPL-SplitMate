package com.splitmate.business.service.impl;

import com.splitmate.db.dto.expense_split.ExpenseSplitInfoDTO;
import com.splitmate.db.dto.expense_split.UpdateExpenseSplitDTO;
import com.splitmate.db.entity.ExpenseSplitEntity;
import com.splitmate.db.dao.ExpenseSplitDAO;
import com.splitmate.mapper.ExpenseSplitMapper;
import com.splitmate.business.service.ExpenseSplitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseSplitServiceImpl implements ExpenseSplitService {

    private final ExpenseSplitDAO expenseSplitDao;
    private final ExpenseSplitMapper expenseSplitMapper;

    @Override
    public ExpenseSplitInfoDTO create(ExpenseSplitEntity expenseSplitEntity) {
        return expenseSplitMapper.entityToDto(expenseSplitDao.save(expenseSplitEntity));
    }

    @Override
    public List<ExpenseSplitInfoDTO> get(final Long expenseId) {

        return expenseSplitDao.findAll().stream()
                .filter(s -> s.getExpenseEntity().getId().equals(expenseId))
                .map(expenseSplitMapper::entityToDto)
                .toList();
    }

    @Override
    public ExpenseSplitInfoDTO update(final Long expenseId, final Long splitId, final UpdateExpenseSplitDTO updateExpenseSplitDto) {

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

