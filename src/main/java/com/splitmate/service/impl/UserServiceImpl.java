package com.splitmate.service.impl;

import com.splitmate.entity.User;
import com.splitmate.dao.UserDao;
import com.splitmate.dto.user.CreateUserDto;
import com.splitmate.dto.user.UpdateUserDto;
import com.splitmate.dto.user.UserInfoDto;
import com.splitmate.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserInfoDto create(CreateUserDto request) {
        if (userDao.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        userDao.save(user);

        return mapToResponse(user);
    }

    @Override
    public List<UserInfoDto> getAll() {
        return userDao.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserInfoDto get(Long id) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    @Override
    public UserInfoDto update(Long id, UpdateUserDto request) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        userDao.save(user);

        return mapToResponse(user);
    }

    @Override
    public void delete(Long id) {
        if (!userDao.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userDao.deleteById(id);
    }

    private UserInfoDto mapToResponse(User user) {
        UserInfoDto res = new UserInfoDto();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        return res;
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }


}

