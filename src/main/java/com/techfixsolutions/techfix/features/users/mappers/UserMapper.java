package com.techfixsolutions.techfix.features.users.mappers;

import com.techfixsolutions.techfix.features.users.dto.UserDto;
import com.techfixsolutions.techfix.features.users.dto.UserResponseDto;
import com.techfixsolutions.techfix.features.users.dto.UserUpdateDto;
import com.techfixsolutions.techfix.features.users.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // dto to entity
    public User toEntity(UserDto dto) {
        return User.builder()
                .fullName(dto.fullName())
                .email(dto.email())
                .role(dto.role())
                .build();
    }

    // entity to dto
    public UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(
                user.getUuid(),
                user.getFullName(),
                user.getEmail(),
                user.getRole()
        );
    }

    // dto to entity (update and patch)
    public void updateEntity(User user, UserUpdateDto dto) {
        if (dto.fullName() != null) {
            user.setFullName(dto.fullName());
        }

        if (dto.email() != null) {
            user.setEmail(dto.email());
        }

        if (dto.role() != null) {
            user.setRole(dto.role());
        }
    }
}
