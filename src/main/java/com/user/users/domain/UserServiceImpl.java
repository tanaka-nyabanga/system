package com.user.users.domain;

import com.user.exception.*;
import com.user.users.service.ChangeUserPasswordCommand;
import com.user.users.service.UpdateUserCommand;
import com.user.users.service.UserCommand;
import com.user.users.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    private boolean isValidPassword(String password) {
        boolean hasNumber = password.matches(".*\\d.*");
        if (!hasNumber) {
            throw new PasswordHasnoNumbersException("Password must contain at least 1 number.");
        }

        boolean hasSpecialChar = password.matches(".*[^a-zA-Z0-9].*");
        if (!hasSpecialChar) {
            throw new PasswordHasNoSpecCharException("Password must contain at least 1 special character.");
        }

        boolean numberOfCharacters = password.length() >= 8;
        if (!numberOfCharacters) {
            throw new PasswordHasFewCharException("Password must contain at least 8 characters.");
        }
        return true;
    }


    //getallusers
    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.toDtos(userRepository.findAll());
    }

    @Override
    public UserDto create(UserCommand userCommand) {

        String encodedPassword = passwordEncoder.encode(userCommand.password());

        if (!isValidPassword(userCommand.password())) {
            throw new IllegalArgumentException("Password does not meet the required criteria.");
        }
        if (userRepository.existsByUsername(userCommand.username())) {
            throw new DuplicateExpception("User with name '" + userCommand.username() + "' already exists.");
        }

        User user = new User();
        user.setUsername(userCommand.username());
        user.setName(userCommand.name());
        user.setRole(Role.USER);
        user.setEmail(userCommand.email());
        user.setPassword(encodedPassword);
        user.setRecoveryAnswer(userCommand.recoveryAnswer());

        User savedUser = userRepository.save(user);
        log.info("User Created");
        return userMapper.toDto(savedUser);

    }

    @Override
    public UserDto update(long id, UpdateUserCommand updateUserCommand) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundUserException("User not found with id: " + id));


        log.info("Looking for user with username: {}", updateUserCommand.username());
        log.info("Looking for user with email: {}", updateUserCommand.email());

        if (updateUserCommand.name() == null || updateUserCommand.name().isEmpty()) {
            throw new MissingFieldException("Name cannot be empty");
        }
        if (updateUserCommand.email() == null || updateUserCommand.email().isEmpty()) {
            throw new MissingFieldException("Email cannot be empty");
        }
        if (updateUserCommand.username() == null || updateUserCommand.username().isEmpty()) {
            throw new MissingFieldException("Username cannot be empty");
        }
        if (userRepository.existsByUsernameAndIdNot(updateUserCommand.username(), id)) {
            throw new DuplicateExpception("User with username '" + updateUserCommand.username() + "' already exists.");
        }
        if (userRepository.existsByEmailAndIdNot(updateUserCommand.email(), id)) {
            throw new DuplicateExpception("User with email '" + updateUserCommand.email() + "' already exists.");
        }

        log.info("##### Updated user: {}", updateUserCommand.name());

        user.setName(updateUserCommand.name());
        user.setEmail(updateUserCommand.email());
        user.setUsername(updateUserCommand.username());

        User updatedUser = userRepository.save(user);

        return userMapper.toDto(updatedUser);


    }

    @PostConstruct
    public void logAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> log.info("Existing user: username={}, email={}", user.getUsername(), user.getEmail()));
    }

    @Override
    public void delete(long id) {
        if (!userRepository.existsById((int) id)) {
            throw new NotFoundUserException("User not found with id: " + id);
        }
        userRepository.deleteById((int) id);
    }


    @Override
    public UserDto findById(long id) {
        User user = userRepository.findById((int) id)
                .orElseThrow(() -> new NotFoundUserException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    @Override
    public UserDetails findByUserDetailsUsername(String username) {

        return userRepository.findByUsername(username)

                .orElseThrow(() -> new NotFoundUserException("User not found with username: " + username));
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException("User not found with email: " + email));
        return userMapper.toDto(user);
    }

    @Override
    public void save(User user) {

    }

    @Override
    public UserDto updatePasswordword(long id, ChangeUserPasswordCommand changeUserPasswordCommand) {


        final User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundUserException("User not found with id: " + id));

        String databasePassword = user.getPassword();

        String newPassword = changeUserPasswordCommand.newPassword();
        String confirmNewPassword = changeUserPasswordCommand.confirmNewPassword();

        if (!passwordEncoder.matches(changeUserPasswordCommand.oldPassword(), databasePassword)) {
            throw new PasswordMismatchException("Old Passwords dont match");
        }

        if (!isValidPassword(changeUserPasswordCommand.newPassword())) {
            throw new IllegalArgumentException("Password does not meet the required criteria.");
        }
        if (!newPassword.equals(confirmNewPassword)) {
            throw new PasswordMismatchException("New Passwords dont match");
        }

        confirmNewPassword = passwordEncoder.encode(confirmNewPassword);

        user.setPassword(confirmNewPassword);
        User passwordUpdatedUser = userRepository.save(user);
        return userMapper.toDto(passwordUpdatedUser);

    }
}


  /*  @Override public UserDto update(long id, UserCommand userCommand) {
        User existingUser = userRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new NotFoundUser("User not found with id: " + id));

        if (userRepository.existsByUsername(userCommand.username()) && !existingUser.getUsername().equals(userCommand.username())) {
            throw new DuplicateExpception("User with name '" + userCommand.name() + "' already exists.");
        }
        return save(userCommand);

    }*/
   /* private UserDto save(UserCommand userCommand) {
        if (!isValidPassword(userCommand.password())) {
            throw new IllegalArgumentException("Password does not meet the required criteria.");
        }
        val user = new User();
        user.setName(userCommand.name());
        log.info("##### password: {}",userCommand.password());
        user.setPassword(passwordEncoder.encode(userCommand.password()));
        user.setRecoveryAnswer(userCommand.recoveryAnswer());
        user.setRole(Role.USER);
        user.setEmail(userCommand.email());
        user.setUsername(userCommand.username());
        val createdUser = userRepository.save(user);
        return userMapper.toDto(createdUser);
    }*/
