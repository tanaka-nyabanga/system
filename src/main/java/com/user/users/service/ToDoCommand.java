package com.user.users.service;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record ToDoCommand(
        @NotBlank(message = "Task Name cannot be blank")
        String task,
        @NotBlank(message = "Task Description cannot be blank")
        String description
) {
}
