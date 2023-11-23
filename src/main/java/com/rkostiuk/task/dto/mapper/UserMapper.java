package com.rkostiuk.task.dto.mapper;

import com.rkostiuk.task.dto.request.NewUserRequest;
import com.rkostiuk.task.dto.request.PatchUserRequest;
import com.rkostiuk.task.dto.response.UserResponse;
import com.rkostiuk.task.entity.User;

public interface UserMapper {
    User toUser(NewUserRequest newUserRequest);
    User toUser(PatchUserRequest patchUserRequest);
    UserResponse toUserResponse(User user);
}
