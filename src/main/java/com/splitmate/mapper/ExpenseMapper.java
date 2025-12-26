package com.splitmate.mapper;

import com.splitmate.db.dto.expense.CreateExpenseDTO;
import com.splitmate.db.dto.expense.ExpenseInfoDTO;
import com.splitmate.db.dto.expense.UpdateExpenseDTO;
import com.splitmate.db.entity.ExpenseEntity;
import com.splitmate.db.entity.GroupEntity;
import com.splitmate.db.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "groupEntity", source = "groupEntity")
    @Mapping(target = "paidOutBy", source = "paidBy")
    @Mapping(target = "splits", ignore = true)
    ExpenseEntity dtoToEntity(CreateExpenseDTO dto, GroupEntity groupEntity, UserEntity paidBy);

    @Mapping(source = "groupEntity.id", target = "groupId")
    @Mapping(source = "paidOutBy.id", target = "paidBy")
    ExpenseInfoDTO entityToDto(ExpenseEntity expenseEntity);

    @Mapping(target = "paidOutBy", source = "paidBy")
    @Mapping(target = "splits", ignore = true)
    void updateDtoToEntity(UpdateExpenseDTO dto,
                           UserEntity paidBy,
                           @MappingTarget ExpenseEntity expenseEntity);
}

