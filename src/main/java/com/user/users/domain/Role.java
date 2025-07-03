package com.user.users.domain;

import jakarta.validation.constraints.NotBlank;

public enum Role {
    @NotBlank(message = "Role cannot be blank")
    USER,
    @NotBlank(message = "Role cannot be blank")

    ADMIN
}

