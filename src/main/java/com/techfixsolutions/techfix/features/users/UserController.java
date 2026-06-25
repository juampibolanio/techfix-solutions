package com.techfixsolutions.techfix.features.users;

import com.techfixsolutions.techfix.features.users.dto.UserDto;
import com.techfixsolutions.techfix.features.users.dto.UserUpdateDto;
import com.techfixsolutions.techfix.features.users.models.User;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping
  public List<User> findAll() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<User> findById(@PathVariable UUID uuid) {
    return userService.findById(uuid);
  }

  @PostMapping
  public User create(@Valid @RequestBody UserDto dto) {
    return userService.create(dto);
  }

  @PatchMapping("/{id}")
  public User patch(@PathVariable UUID uuid, @Valid @RequestBody UserUpdateDto dto) {
    return userService.patch(uuid, dto);
  }

  @DeleteMapping("/{id{")
  public void delete(@PathVariable UUID uuid) {
    userService.delete(uuid);
  }
}
