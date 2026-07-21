package com.techfixsolutions.techfix.features.users.dto;

import java.util.UUID;

public record UserSummaryDto(UUID uuid, String name, String email) {
}
