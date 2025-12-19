package com.splitmate.mapper;

import com.splitmate.dto.expense.CreateExpenseDto;
import com.splitmate.dto.expense.ExpenseInfoDto;
import com.splitmate.dto.expense.UpdateExpenseDto;
import com.splitmate.entity.Expense;
import com.splitmate.entity.Group;
import com.splitmate.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "group", source = "group")
    @Mapping(target = "paidOutBy", source = "paidBy")
    @Mapping(target = "splits", ignore = true)
    Expense dtoToEntity(CreateExpenseDto dto, Group group, User paidBy);

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "paidOutBy.id", target = "paidBy")
    ExpenseInfoDto entityToDto(Expense expense);

    @Mapping(target = "paidOutBy", source = "paidBy")
    @Mapping(target = "splits", ignore = true)
    void updateDtoToEntity(UpdateExpenseDto dto,
                      User paidBy,
                      @MappingTarget Expense expense);
}

