package com.magnesiatech.springaopexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception exception){
        return new ResponseEntity<>(createError("E-001", "UNEXPECTED_ERROR", exception),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Error> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        FieldError error = exception.getBindingResult().getFieldErrors().get(0);

        Error response = Error.builder().title("INVALID-FIELD")
                .code("F-001")
                .details(error.getField() + "-" + error.getDefaultMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Error createError(String code, String tittle, Exception e) {
        return Error.builder().code(code).title(tittle).details(e.getMessage()).build();
    }

}
