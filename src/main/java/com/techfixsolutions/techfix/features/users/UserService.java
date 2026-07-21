package com.techfixsolutions.techfix.features.users;

import com.techfixsolutions.techfix.features.users.dto.UserDto;
import com.techfixsolutions.techfix.features.users.dto.UserResponseDto;
import com.techfixsolutions.techfix.features.users.dto.UserUpdateDto;
import com.techfixsolutions.techfix.features.users.exceptions.EmailAlreadyExistsException;
import com.techfixsolutions.techfix.features.users.exceptions.UserNotFoundException;
import com.techfixsolutions.techfix.features.users.mappers.UserMapper;
import com.techfixsolutions.techfix.features.users.models.User;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository repository;
  private final UserMapper mapper;

  public List<UserResponseDto> findAll() {
    return repository.findAll()
            .stream()
            .map((mapper::toResponseDto))
            .toList();
  }

  public UserResponseDto findById(UUID uuid) {
    User user = repository.findById(uuid)
            .orElseThrow(() -> new UserNotFoundException("User with id " + uuid + " not found"));

    return mapper.toResponseDto(user);
  }

  public UserResponseDto create(UserDto dto) {
    if (repository.existsByEmail(dto.email())) {
      throw new EmailAlreadyExistsException("Email already registered");
    }

    User mappedUser = mapper.toEntity(dto);

    User savedUser = repository.save(mappedUser);

    return mapper.toResponseDto(savedUser);
  }

  public UserResponseDto patch(UUID uuid, UserUpdateDto dto) {
    User currentUser = repository.findById(uuid)
        .orElseThrow(() -> new UserNotFoundException("User not found"));

    mapper.updateEntity(currentUser, dto);

    User updatedUser = repository.save(currentUser);

    return mapper.toResponseDto(updatedUser);
  }

  public void delete(UUID uuid) {
    if (!repository.existsById(uuid)) {
      throw new UserNotFoundException("User with id " + uuid + " not found");
    }

    repository.deleteById(uuid);
  }
}
