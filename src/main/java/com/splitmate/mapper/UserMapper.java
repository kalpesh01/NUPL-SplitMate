package com.splitmate.mapper;

import com.splitmate.db.dto.user.CreateUserDto;
import com.splitmate.db.dto.user.UpdateUserDto;
import com.splitmate.db.dto.user.UserInfoDto;
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
    UserEntity dtoToEntity(CreateUserDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "memberships", ignore = true)
    @Mapping(target = "expensesPaid", ignore = true)
    void updateDtoToEntity(UpdateUserDto dto, @MappingTarget UserEntity entity);

    UserInfoDto entityToDto(UserEntity user);

}

