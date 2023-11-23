package com.rkostiuk.task.dto.mapper;

import com.rkostiuk.task.dto.request.NewUserRequest;
import com.rkostiuk.task.dto.request.PatchUserRequest;
import com.rkostiuk.task.dto.response.UserResponse;
import com.rkostiuk.task.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserModelMapper implements UserMapper {
    private final ModelMapper modelMapper;

    public UserModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public User toUser(NewUserRequest newUserRequest) {
        return modelMapper.map(newUserRequest, User.class);
    }

    @Override
    public User toUser(PatchUserRequest patchUserRequest) {
        return modelMapper.map(patchUserRequest, User.class);
    }

    @Override
    public UserResponse toUserResponse(User user) {
        return new UserResponse(user);
    }
}
