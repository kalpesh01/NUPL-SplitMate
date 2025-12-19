package com.splitmate.mapper;

import com.splitmate.db.dto.expense_split.ExpenseSplitInfoDto;
import com.splitmate.db.dto.expense_split.UpdateExpenseSplitDto;
import com.splitmate.db.entity.ExpenseSplitEntity;
import com.splitmate.enums.PaymentStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", imports = PaymentStatus.class)
public interface ExpenseSplitMapper {

    @Mapping(source = "expenseEntity.id", target = "expenseId")
    @Mapping(source = "ownBy.id", target = "userId")
    ExpenseSplitInfoDto entityToDto(ExpenseSplitEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "expenseEntity", ignore = true)
    @Mapping(target = "ownBy", ignore = true)
    @Mapping(target = "splitAmount", source = "shareAmount")
    @Mapping(
            target = "paymentStatus",
            expression = "java(PaymentStatus.valueOf(dto.getPaymentStatus()))"
    )
    void updateDtoToEntity(UpdateExpenseSplitDto dto,
                           @MappingTarget ExpenseSplitEntity entity);
}

