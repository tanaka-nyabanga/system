package com.user.users.domain;

import com.user.users.service.UserCommand;

import java.util.List;

public interface UserMapper {
    UserDto toDto(User user);
    List<UserDto> toDtos(List<User> users);
    User toEntity(UserCommand UserCommand);
}
