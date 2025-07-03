package com.user.users.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDto {
    Long id;
  //  @NotBlank(message = "Name cannot be blank")
    String name;
    Role role;
  //  @NotBlank(message = "Username cannot be blank")
    String username;
 //   @NotBlank(message = "Email cannot be blank")
    String email;
}
