package com.user.users.service;

import com.user.users.domain.User;
import com.user.users.domain.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto create(UserCommand userCommand);

    UserDto update(long id, UpdateUserCommand updateUserCommand);

    void delete(long id);

    UserDto findById(long id);

    UserDetails findByUserDetailsUsername(String username);

    UserDto findByEmail(String email);

    void save(User user);

    UserDto updatePasswordword(long id, ChangeUserPasswordCommand changeUserPasswordCommand);
}
