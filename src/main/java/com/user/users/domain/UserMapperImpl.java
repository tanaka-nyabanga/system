package com.user.users.domain;

import com.user.users.service.UserCommand;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapperImpl implements UserMapper {

    //COnvert Entity into Dto
    public UserDto toDto(User user) {

        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public List<UserDto> toDtos(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            final User user = users.get(i);
            final UserDto dto = toDto(user);
            userDtos.add(dto);

        }
        return userDtos;
    }

    //COnverts from DTO into Entity
    public User toEntity(UserCommand dto) {
        if (dto == null) {
            return null;
        }

        return null;
    }

}
