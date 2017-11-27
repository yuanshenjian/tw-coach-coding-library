package com.thoughtworks.star.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    // TODO students implement
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({SQLException.class, InvalidCredentialException.class})
    public String handleSQLException(InvalidCredentialException exception) {
        return exception.getMessage();
    }

    // TODO students implement
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({BadCredentialsException.class})
    public void handleBadCredentialsException(BadCredentialsException exception) {
        log.error(exception.getMessage());
    }

}
