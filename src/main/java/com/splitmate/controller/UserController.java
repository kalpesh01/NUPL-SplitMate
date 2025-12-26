package com.splitmate.controller;

import com.splitmate.business.service.UserService;
import com.splitmate.db.dto.user.CreateUserDTO;
import com.splitmate.db.dto.user.UpdateUserDTO;
import com.splitmate.db.dto.user.UserInfoDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController
{

    private final UserService userService;

    @PostMapping
    public UserInfoDTO create(@Valid @RequestBody final CreateUserDTO userRequest)
    {
        return userService.create(userRequest);
    }

    @GetMapping("/{userId}")
    public UserInfoDTO get(@PathVariable final Long userId)
    {
        return userService.get(userId);
    }

    @GetMapping
    public List<UserInfoDTO> all()
    {
        return userService.getAll();
    }

    @PutMapping("/{userId}")
    public UserInfoDTO updateUser(@PathVariable final Long userId, @RequestBody final UpdateUserDTO request)
    {
        return userService.update(userId, request);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable final Long userId)
    {
        userService.delete(userId);
    }
}
