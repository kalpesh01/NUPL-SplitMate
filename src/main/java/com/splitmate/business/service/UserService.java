package com.splitmate.business.service;

import com.splitmate.db.dto.user.CreateUserDTO;
import com.splitmate.db.dto.user.UpdateUserDTO;
import com.splitmate.db.dto.user.UserInfoDTO;
import com.splitmate.db.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserInfoDTO create(final CreateUserDTO request);

    List<UserInfoDTO> getAll();

    UserInfoDTO get(final Long id);

    UserInfoDTO update(final Long id, final UpdateUserDTO request);

    void delete(final Long id);

    UserEntity findById(final Long id);

    List<UserEntity> findAll();

    List<UserEntity> findAllByIdIn(final List<Long> userIds);
}

