package com.user.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    public String getRecoverAnswer;
    private String name;
    private String password;
    private String email;
    private String username;
}
