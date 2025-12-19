package com.splitmate.controller;

import com.splitmate.dto.user.CreateUserDto;
import com.splitmate.dto.user.UpdateUserDto;
import com.splitmate.dto.user.UserInfoDto;
import com.splitmate.service.GroupMemberService;
import com.splitmate.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserInfoDto create(@Valid @RequestBody final CreateUserDto userRequest) {  //remove the ResponseEntity // add @Valid
        return userService.create(userRequest);
    }

    @GetMapping("/{userId}")
    public UserInfoDto get(@PathVariable final Long userId) {
        return userService.get(userId);
    }

    @GetMapping
    public List<UserInfoDto> all() {
        return userService.getAll();
    }

    @PutMapping("/{userId}")
    public UserInfoDto updateUser(
            @PathVariable final Long userId,
            @RequestBody final UpdateUserDto request
    ) {
        return userService.update(userId, request);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable final Long userId) {
        userService.delete(userId);
    }
}
