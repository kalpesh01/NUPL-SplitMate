package com.splitmate.service;

import com.splitmate.dto.request.CreateUserRequest;
import com.splitmate.dto.request.UpdateUserRequest;
import com.splitmate.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUser(Long id);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    void deleteUser(Long id);
}

