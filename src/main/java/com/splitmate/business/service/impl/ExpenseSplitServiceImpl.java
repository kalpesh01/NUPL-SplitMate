package com.splitmate.business.service.impl;

import com.splitmate.business.mapper.ExpenseSplitMapper;
import com.splitmate.business.service.ExpenseSplitService;
import com.splitmate.db.dao.ExpenseSplitDAO;
import com.splitmate.db.dto.expense_split.ExpenseSplitInfoDTO;
import com.splitmate.db.dto.expense_split.UpdateExpenseSplitDTO;
import com.splitmate.db.entity.ExpenseSplitEntity;

import org.springframework.stereotype.Service;

import java.util.List;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExpenseSplitServiceImpl implements ExpenseSplitService
{

    private final ExpenseSplitDAO expenseSplitDao;
    private final ExpenseSplitMapper expenseSplitMapper;

    @Override
    public ExpenseSplitInfoDTO create(ExpenseSplitEntity expenseSplitEntity)
    {
        return expenseSplitMapper.mapEntityToDTO(expenseSplitDao.save(expenseSplitEntity));
    }

    @Override
    public List<ExpenseSplitInfoDTO> get(final Long expenseId)
    {

        return expenseSplitDao.findAll().stream().filter(s -> s.getExpenseEntity().getId().equals(expenseId)).map(expenseSplitMapper::mapEntityToDTO).toList();
    }

    @Override
    public ExpenseSplitInfoDTO update(final Long expenseId, final Long splitId, final UpdateExpenseSplitDTO updateExpenseSplitDto)
    {

        ExpenseSplitEntity split = expenseSplitDao.findById(splitId).orElseThrow(() -> new RuntimeException("Split not found"));

        expenseSplitMapper.mapUpdateDTOToEntity(updateExpenseSplitDto, split);
        expenseSplitDao.save(split);

        return expenseSplitMapper.mapEntityToDTO(split);
    }

    @Override
    public void delete(final Long expenseId, final Long splitId)
    {
        expenseSplitDao.deleteById(splitId);
    }
}

