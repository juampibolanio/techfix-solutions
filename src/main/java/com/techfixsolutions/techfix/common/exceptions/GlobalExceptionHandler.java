package com.techfixsolutions.techfix.common.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

import com.techfixsolutions.techfix.features.users.exceptions.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.techfixsolutions.techfix.features.users.exceptions.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.exc.InvalidFormatException;

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

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailAlreadyExists(
            EmailAlreadyExistsException ex, HttpServletRequest request) {

        ErrorResponseDto error = ErrorResponseDto.builder()
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpServletRequest request) {

        String message = "Data format cannot be valid";

        ErrorResponseDto error = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
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
