package com.user.users.service;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserCommand(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Username cannot be blank")
        String username,
        @NotBlank(message = "Email cannot be blank")
        String email) {

}
