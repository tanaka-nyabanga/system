package com.user.users.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record UserCommand(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Password cannot be blank")
        String password,
        @NotBlank(message = "Recovery Answer cannot be blank")
        String recoveryAnswer,
        @NotBlank(message = "Username cannot be blank")
        String username,
        @NotBlank(message = "Email cannot be blank")
        String email) {
}
