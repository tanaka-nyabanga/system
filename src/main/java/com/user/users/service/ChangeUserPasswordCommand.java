package com.user.users.service;

import jakarta.validation.constraints.NotBlank;

public record ChangeUserPasswordCommand(@NotBlank(message = "old Password cannot be blank")

                                        String oldPassword, @NotBlank(message = "New Password cannot be blank")

                                        String newPassword, @NotBlank(message = "Confirmed Password cannot be blank")

                                        String confirmNewPassword) {
}
