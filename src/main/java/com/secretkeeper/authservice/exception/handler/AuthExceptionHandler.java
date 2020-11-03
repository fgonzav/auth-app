package com.secretkeeper.authservice.exception.handler;

import com.secretkeeper.authservice.domain.Error;
import com.secretkeeper.authservice.exception.BadUserCredentialException;
import com.secretkeeper.authservice.exception.UserDisabledException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AuthExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BadUserCredentialException.class)
    public ResponseEntity<Error> badCredentials(BadUserCredentialException e){
        return ResponseEntity.badRequest().body(new Error("Invalid user or password"));
    }

    @ResponseBody
    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<Error> userDisabled(UserDisabledException e){
        return ResponseEntity.badRequest().body(new Error("User disabled"));
    }
}
