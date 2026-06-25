package com.techfixsolutions.techfix.common.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.techfixsolutions.techfix.features.users.exceptions.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UserNotFoundException exception, HttpServletRequest request) {
        ErrorResponseDto error = ErrorResponseDto.builder()
                    .message(exception.getMessage())
                    .status(HttpStatus.NOT_FOUND.value())
                    .path(request.getRequestURI())
                    .timestamp(LocalDateTime.now())
                    .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception exception, HttpServletRequest request) {
        ErrorResponseDto error = ErrorResponseDto.builder()
                    .message("An error has occurred")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .path(request.getRequestURI())
                    .timestamp(LocalDateTime.now())
                    .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
