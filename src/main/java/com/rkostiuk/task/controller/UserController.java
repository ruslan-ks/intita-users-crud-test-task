package com.rkostiuk.task.controller;

import com.rkostiuk.task.dto.mapper.UserMapper;
import com.rkostiuk.task.dto.request.NewUserRequest;
import com.rkostiuk.task.dto.request.PatchUserRequest;
import com.rkostiuk.task.dto.response.UserResponse;
import com.rkostiuk.task.entity.User;
import com.rkostiuk.task.service.UserService;
import com.rkostiuk.task.validation.UserValidator;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserValidator userValidator;

    public UserController(UserService userService, UserMapper userMapper, UserValidator userValidator) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userValidator = userValidator;
    }

    @GetMapping
    public Page<UserResponse> findPage(Pageable pageable) {
        return userService.findUserPage(pageable)
                .map(userMapper::toUserResponse);
    }

    @GetMapping("/{userId}")
    public UserResponse findById(@PathVariable long userId) {
        User user = userService.findUserById(userId);
        return userMapper.toUserResponse(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponse create(@Valid @RequestBody NewUserRequest newUserRequest) {
        userValidator.validate(newUserRequest);
        User user = userMapper.toUser(newUserRequest);

        user = userService.addUser(user);
        return userMapper.toUserResponse(user);
    }

    @PatchMapping("/{userId}")
    public void patch(@PathVariable long userId, @Valid @RequestBody PatchUserRequest patchRequest) {
        User user = userMapper.toUser(patchRequest);
        user.setId(userId);
        userService.patchUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable long userId) {
        userService.deleteUserById(userId);
    }
}
