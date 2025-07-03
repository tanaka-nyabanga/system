package com.user.users.api;

import com.user.users.domain.User;
import com.user.users.domain.UserDto;
import com.user.users.service.ChangeUserPasswordCommand;
import com.user.users.service.UpdateUserCommand;
import com.user.users.service.UserCommand;
import com.user.users.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Tag(name = "User APIs",description = "API Documentation for Users")
public class UsersController {

    private final UserService userService;

    @PostMapping("/register")
    public UserDto create(@Valid @RequestBody  UserCommand userCommand) {
        return userService.create(userCommand);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/update/{id}")
    public UserDto update(
            @PathVariable long id,
            @Valid @RequestBody UpdateUserCommand updateUserCommand) {
        return userService.update(id, updateUserCommand);
    }

    @PatchMapping("/update/password/{id}")
    public UserDto updatePassword(
            @PathVariable long id,
            @Valid  @RequestBody ChangeUserPasswordCommand changeUserPasswordCommand)
    {
        return userService.updatePasswordword(id, changeUserPasswordCommand);
    }


}
