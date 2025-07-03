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

    User toEntity(UserCommand userCommand);

    @Mapping (target = "password", ignore = true)
    @Mapping (target = "recoveryAnswer", ignore = true)
    void updateUser (@MappingTarget User user, UpdateUserCommand updateUserCommand);
}
