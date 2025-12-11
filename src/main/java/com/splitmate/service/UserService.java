package com.splitmate.service;

import com.splitmate.dto.user.CreateUserDto;
import com.splitmate.dto.user.UpdateUserDto;
import com.splitmate.dto.user.UserInfoDto;
import com.splitmate.entity.User;

import java.util.List;

public interface UserService {

    UserInfoDto create(CreateUserDto request);

    List<UserInfoDto> getAll();

    UserInfoDto get(Long id);

    UserInfoDto update(Long id, UpdateUserDto request);

    void delete(Long id);

    User findById(Long id);

    List<User> findAll();
}

