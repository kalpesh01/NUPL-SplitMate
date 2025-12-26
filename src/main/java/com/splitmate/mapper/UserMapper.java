package com.splitmate.mapper;

import com.splitmate.db.dto.user.CreateUserDTO;
import com.splitmate.db.dto.user.UpdateUserDTO;
import com.splitmate.db.dto.user.UserInfoDTO;
import com.splitmate.db.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "memberships", ignore = true)
    @Mapping(target = "expensesPaid", ignore = true)
    UserEntity dtoToEntity(CreateUserDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "memberships", ignore = true)
    @Mapping(target = "expensesPaid", ignore = true)
    void updateDtoToEntity(UpdateUserDTO dto, @MappingTarget UserEntity entity);

    UserInfoDTO entityToDto(UserEntity user);

}

