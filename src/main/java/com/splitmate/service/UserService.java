package com.splitmate.service;

import com.splitmate.dto.user.CreateUserDto;
import com.splitmate.dto.user.UpdateUserDto;
import com.splitmate.dto.user.UserInfoDto;
import com.splitmate.entity.User;

import java.util.List;

public interface UserService {

    UserInfoDto create(final CreateUserDto request);

    List<UserInfoDto> getAll();

    UserInfoDto get(final Long id);

    UserInfoDto update(final Long id, final UpdateUserDto request);

    void delete(final Long id);

    User findById(final Long id);

    List<User> findAll();

    List<User> findAllByIdIn(final List<Long> userIds);
}

