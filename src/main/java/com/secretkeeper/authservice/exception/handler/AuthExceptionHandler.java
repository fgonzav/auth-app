package com.secretkeeper.authservice.exception.handler;

import com.secretkeeper.authservice.domain.Error;
import com.secretkeeper.authservice.exception.BadUserCredentialException;
import com.secretkeeper.authservice.exception.UserDisabledException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Priority;

@Priority(1)
@Slf4j
@ControllerAdvice
public class AuthExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BadUserCredentialException.class)
    public ResponseEntity<Error> badCredentials(BadUserCredentialException e){
        return buildResponseEntity(HttpStatus.BAD_REQUEST,new Error("Invalid user or password"), e);
    }

    @ResponseBody
    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<Error> userDisabled(UserDisabledException e){
        return buildResponseEntity(HttpStatus.BAD_REQUEST,new Error("User disabled"), e);
    }

    private ResponseEntity<Error> buildResponseEntity(HttpStatus status, Error body, Exception e){
        log.error(body.getError(), e);
        return ResponseEntity.status(status).body(body);
    }
}
