package com.splitmate.business.service.impl;

import com.splitmate.business.mapper.UserMapper;
import com.splitmate.business.service.UserService;
import com.splitmate.db.dao.UserDAO;
import com.splitmate.db.dto.user.CreateUserDTO;
import com.splitmate.db.dto.user.UpdateUserDTO;
import com.splitmate.db.dto.user.UserInfoDTO;
import com.splitmate.db.entity.UserEntity;
import com.splitmate.enums.ErrorCodes;
import com.splitmate.exception.error.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService
{

    private final UserDAO userDao;
    private final UserMapper userMapper;

    @Override
    public UserInfoDTO create(final CreateUserDTO createUserDto)
    {
        if (userDao.existsByEmail(createUserDto.getEmail()))
        {
            throw new ResourceNotFoundException(ErrorCodes.EMAIL_ALREADY_EXIST.getMessage());
        }

        UserEntity user = userMapper.mapDTOToEntity(createUserDto);
        userDao.save(user);

        return userMapper.mapEntityToDTO(user);
    }

    @Override
    public List<UserInfoDTO> getAll()
    {
        return userDao.findAll().stream().map(userMapper::mapEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public UserInfoDTO get(final Long id)
    {
        UserEntity user = userDao.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage()));

        return userMapper.mapEntityToDTO(user);
    }

    @Override
    public UserInfoDTO update(final Long id, final UpdateUserDTO updateUserDto)
    {
        UserEntity user = userDao.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage()));

        userMapper.mapUpdateDTOToEntity(updateUserDto, user);
        userDao.save(user);

        return userMapper.mapEntityToDTO(user);
    }

    @Override
    public void delete(final Long id)
    {
        if (!userDao.existsById(id))
        {
            throw new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage());
        }

        userDao.deleteById(id);
    }

    @Override
    public UserEntity findById(final Long id)
    {
        return userDao.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.USERS_NOT_FOUND.getMessage()));
    }

    @Override
    public List<UserEntity> findAll()
    {
        return userDao.findAll();
    }

    @Override
    public List<UserEntity> findAllByIdIn(final List<Long> userIds)
    {
        return userDao.findAllById(userIds);
    }

}

