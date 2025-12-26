package com.splitmate.mapper;

import com.splitmate.db.dto.expense_split.ExpenseSplitInfoDTO;
import com.splitmate.db.dto.expense_split.UpdateExpenseSplitDTO;
import com.splitmate.db.entity.ExpenseEntity;
import com.splitmate.db.entity.ExpenseSplitEntity;
import com.splitmate.db.entity.UserEntity;
import com.splitmate.enums.PaymentStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", imports = PaymentStatus.class)
public interface ExpenseSplitMapper {

    @Mapping(source = "expenseEntity.id", target = "expenseId")
    @Mapping(source = "ownBy.id", target = "userId")
    ExpenseSplitInfoDTO entityToDto(ExpenseSplitEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "expenseEntity", ignore = true)
    @Mapping(target = "ownBy", ignore = true)
    @Mapping(target = "splitAmount", source = "shareAmount")
    @Mapping(
            target = "paymentStatus",
            expression = "java(PaymentStatus.valueOf(dto.getPaymentStatus()))"
    )
    void updateDtoToEntity(UpdateExpenseSplitDTO dto,
                           @MappingTarget ExpenseSplitEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "expenseEntity", source = "expenseEntity")
    @Mapping(target = "ownBy", source = "userEntity")
    @Mapping(target = "splitAmount", source = "splitAmount")
    @Mapping(target = "paymentStatus", source = "paymentStatus")
    ExpenseSplitEntity toExpenseSplitEntity(ExpenseEntity expenseEntity, UserEntity userEntity, double splitAmount, PaymentStatus paymentStatus);
}

