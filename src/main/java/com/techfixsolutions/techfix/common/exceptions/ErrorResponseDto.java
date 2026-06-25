package com.techfixsolutions.techfix.common.exceptions;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {
    private String message;
    private int status;
    private String path;
    private LocalDateTime timestamp;
}
