package com.splitmate.mapper;

import com.splitmate.dto.user.CreateUserDto;
import com.splitmate.dto.user.UpdateUserDto;
import com.splitmate.dto.user.UserInfoDto;
import com.splitmate.entity.User;
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
    User dtoToEntity(CreateUserDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "memberships", ignore = true)
    @Mapping(target = "expensesPaid", ignore = true)
    void updateDtoToEntity(UpdateUserDto dto, @MappingTarget User entity);

    UserInfoDto entityToDto(User user);

}

