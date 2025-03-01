package dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.config;

import dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.common.ExceptionUtils;
import dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.dto.ErrorDto;
import dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        return ResponseEntity.badRequest().body(
                ErrorDto.builder()
                        .message(userNotFoundException.getMessage())
                        .details(ExceptionUtils.getStackTrace(userNotFoundException)));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleUserNotFoundException(RuntimeException rex) {
        return ResponseEntity.internalServerError().body(
                ErrorDto.builder()
                        .message(rex.getMessage())
                        .details(ExceptionUtils.getStackTrace(rex)));
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException(Exception rex) {
        return ResponseEntity.internalServerError().body(
                ErrorDto.builder()
                        .message(rex.getMessage())
                        .details(ExceptionUtils.getStackTrace(rex)));
    }
}
