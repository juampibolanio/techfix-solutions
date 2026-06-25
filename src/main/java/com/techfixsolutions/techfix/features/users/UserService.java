package com.techfixsolutions.techfix.features.users;

import com.techfixsolutions.techfix.features.users.dto.UserDto;
import com.techfixsolutions.techfix.features.users.dto.UserUpdateDto;
import com.techfixsolutions.techfix.features.users.exceptions.UserNotFoundException;
import com.techfixsolutions.techfix.features.users.models.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public Optional<User> findById(UUID uuid) {
    return userRepository.findById(uuid);
  }

  public User create(UserDto dto) {
    User newUser = User.builder().fullName(dto.fullName()).email(dto.email()).build();

    return userRepository.save(newUser);
  }

  public User patch(UUID uuid, UserUpdateDto dto) {
    User currentUser = userRepository
        .findById(uuid)
        .orElseThrow(() -> new UserNotFoundException("User not found"));

    if (dto.fullName() != null) {
      currentUser.setFullName(dto.fullName());
    }

    if (dto.email() != null) {
      currentUser.setEmail(dto.email());
    }

    if (dto.role() != null) {
      currentUser.setRole(dto.role());
    }

    return userRepository.save(currentUser);
  }

  public void delete(UUID uuid) {

    if (!userRepository.existsById(uuid)) {
      throw new UserNotFoundException("User with id " + uuid + " not found");
    }

    userRepository.deleteById(uuid);
  }
}
