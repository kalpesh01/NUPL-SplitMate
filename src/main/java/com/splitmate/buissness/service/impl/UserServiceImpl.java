package com.splitmate.buissness.service.impl;

import com.splitmate.db.entity.UserEntity;
import com.splitmate.db.dao.UserDao;
import com.splitmate.db.dto.user.CreateUserDto;
import com.splitmate.db.dto.user.UpdateUserDto;
import com.splitmate.db.dto.user.UserInfoDto;
import com.splitmate.enums.ErrorCodes;
import com.splitmate.exception.error.ResourceNotFoundException;
import com.splitmate.mapper.UserMapper;
import com.splitmate.buissness.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    @Override
    public UserInfoDto create(final CreateUserDto createUserDto) {
        if (userDao.existsByEmail(createUserDto.getEmail())) {
            throw new ResourceNotFoundException(ErrorCodes.EMAIL_ALREADY_EXIST.getMessage());
        }

        UserEntity user = userMapper.dtoToEntity(createUserDto);
        userDao.save(user);

        return userMapper.entityToDto(user);
    }

    @Override
    public List<UserInfoDto> getAll() {
        return userDao.findAll()
                .stream()
                .map(userMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserInfoDto get(final Long id) {
        UserEntity user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage()));

        return userMapper.entityToDto(user);
    }

    @Override
    public UserInfoDto update(final Long id, final UpdateUserDto updateUserDto) {
        UserEntity user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage()));

        userMapper.updateDtoToEntity(updateUserDto,user);
        userDao.save(user);

        return userMapper.entityToDto(user);
    }

    @Override
    public void delete(final Long id) {
        if (!userDao.existsById(id)) {
            throw new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage());
        }

        userDao.deleteById(id);
    }

    @Override
    public UserEntity findById(final Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage()));
    }

    @Override
    public List<UserEntity> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<UserEntity> findAllByIdIn(final List<Long> userIds) {
        return userDao.findAllById(userIds);
    }


}

