package com.techfixsolutions.techfix.features.users;

import com.techfixsolutions.techfix.features.users.dto.UserDto;
import com.techfixsolutions.techfix.features.users.dto.UserResponseDto;
import com.techfixsolutions.techfix.features.users.dto.UserUpdateDto;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService service;

  @GetMapping
  public ResponseEntity<List<UserResponseDto>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{uuid}")
  public ResponseEntity<UserResponseDto> findById(@PathVariable UUID uuid) {
    return ResponseEntity.ok(service.findById(uuid));
  }

  @PostMapping
  public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserDto dto) {
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.create(dto));
  }

  @PatchMapping("/{uuid}")
  public ResponseEntity<UserResponseDto> patch(@PathVariable UUID uuid, @Valid @RequestBody UserUpdateDto dto) {
    return ResponseEntity.ok(service.patch(uuid, dto));
  }

  @DeleteMapping("/{uuid}")
  public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
    service.delete(uuid);

    return ResponseEntity.noContent().build();
  }
}
