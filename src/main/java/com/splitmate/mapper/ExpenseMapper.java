package com.splitmate.mapper;

import com.splitmate.db.dto.expense.CreateExpenseDto;
import com.splitmate.db.dto.expense.ExpenseInfoDto;
import com.splitmate.db.dto.expense.UpdateExpenseDto;
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
    ExpenseEntity dtoToEntity(CreateExpenseDto dto, GroupEntity groupEntity, UserEntity paidBy);

    @Mapping(source = "groupEntity.id", target = "groupId")
    @Mapping(source = "paidOutBy.id", target = "paidBy")
    ExpenseInfoDto entityToDto(ExpenseEntity expenseEntity);

    @Mapping(target = "paidOutBy", source = "paidBy")
    @Mapping(target = "splits", ignore = true)
    void updateDtoToEntity(UpdateExpenseDto dto,
                      UserEntity paidBy,
                      @MappingTarget ExpenseEntity expenseEntity);
}

