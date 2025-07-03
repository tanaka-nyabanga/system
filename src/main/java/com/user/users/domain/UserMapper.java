package com.user.users.domain;

import com.user.users.service.ChangeUserPasswordCommand;
import com.user.users.service.UpdateUserCommand;
import com.user.users.service.UserCommand;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDto toDto(User user);
    List<UserDto> toDtos(List<User> users);

    @Mapping (target = "password", ignore = true)
    User toEntity(UserCommand userCommand);

    void updateUser (@MappingTarget User user, UpdateUserCommand updateUserCommand);
}
