package com.user.authentication.service;

import com.user.config.JwtService;
import com.user.authentication.AuthenticationRequest;
import com.user.authentication.AuthenticationResponse;
import com.user.users.domain.UserDto;
import com.user.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        log.info("Authenticating user with username: {}", request.getUsername());

        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Invalid username or password");
        }

        log.info("##### Authenticated user: {}", request.getUsername());

        UserDetails user = userService.findByUserDetailsUsername(request.getUsername());
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
