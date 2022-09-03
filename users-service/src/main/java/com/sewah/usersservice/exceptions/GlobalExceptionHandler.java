package com.sewah.usersservice.exceptions;

import com.sewah.usersservice.dtos.ErrorResponse;
import com.sewah.usersservice.exceptions.errors.ResourceAlreadyExistsException;
import com.sewah.usersservice.exceptions.errors.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> runTimeExceptionHandler(RuntimeException ex){
        log.error(String.format("Exception occurred: [ %s ] resulted in: [ %s ]", ex.getLocalizedMessage(), ex.getClass().getName()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .error("Internal Server Error")
                        .message(ex.getLocalizedMessage())
                        .build()
                );
    }

    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    public ResponseEntity<?> resourceAlreadyExistsExceptionHandler(ResourceAlreadyExistsException ex){
        log.error(String.format("Exception occurred: [ %s ] resulted in: [ %s ]", ex.getLocalizedMessage(), ex.getClass().getName()));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder()
                        .error("Cannot Create resource")
                        .message(ex.getLocalizedMessage())
                        .build()
                );
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceAlreadyExistsException ex){
        log.error(String.format("Exception occurred: [ %s ] resulted in: [ %s ]", ex.getLocalizedMessage(), ex.getClass().getName()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .error("Resource not found")
                        .message(ex.getLocalizedMessage())
                        .build()
                );
    }
}
