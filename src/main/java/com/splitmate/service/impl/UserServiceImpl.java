package com.splitmate.service.impl;

import com.splitmate.entity.User;
import com.splitmate.dao.UserDao;
import com.splitmate.dto.user.CreateUserDto;
import com.splitmate.dto.user.UpdateUserDto;
import com.splitmate.dto.user.UserInfoDto;
import com.splitmate.enums.ErrorCodes;
import com.splitmate.exception.error.ResourceNotFoundException;
import com.splitmate.mapper.UserMapper;
import com.splitmate.service.UserService;
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

        User user = userMapper.dtoToEntity(createUserDto);
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
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage()));

        return userMapper.entityToDto(user);
    }

    @Override
    public UserInfoDto update(final Long id, final UpdateUserDto updateUserDto) {
        User user = userDao.findById(id)
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
    public User findById(final Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage()));
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findAllByIdIn(final List<Long> userIds) {
        return userDao.findAllById(userIds);
    }


}

