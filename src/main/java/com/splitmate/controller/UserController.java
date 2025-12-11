package com.splitmate.controller;

import com.splitmate.dto.user.CreateUserDto;
import com.splitmate.dto.user.UpdateUserDto;
import com.splitmate.dto.user.UserInfoDto;
import com.splitmate.service.GroupMemberService;
import com.splitmate.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService service) {
        this.userService = service;
    }

    @PostMapping
    public UserInfoDto create(@Valid @RequestBody CreateUserDto userRequest) {  //remove the ResponseEntity // add @Valid
        return userService.create(userRequest);
    }

    @GetMapping("/{userId}")
    public UserInfoDto get(@PathVariable Long userId) {
        return userService.get(userId);
    }

    @GetMapping
    public List<UserInfoDto> all() {
        return userService.getAll();
    }

    @PutMapping("/{userId}")
    public UserInfoDto updateUser(
            @PathVariable Long userId,
            @RequestBody UpdateUserDto request
    ) {
        return userService.update(userId, request);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
