package com.splitmate.buissness.service;

import com.splitmate.db.dto.user.CreateUserDto;
import com.splitmate.db.dto.user.UpdateUserDto;
import com.splitmate.db.dto.user.UserInfoDto;
import com.splitmate.db.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserInfoDto create(final CreateUserDto request);

    List<UserInfoDto> getAll();

    UserInfoDto get(final Long id);

    UserInfoDto update(final Long id, final UpdateUserDto request);

    void delete(final Long id);

    UserEntity findById(final Long id);

    List<UserEntity> findAll();

    List<UserEntity> findAllByIdIn(final List<Long> userIds);
}

