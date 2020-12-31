package com.secretkeeper.authservice.exception.handler;

import com.secretkeeper.authservice.domain.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Priority;

@Priority(Integer.MAX_VALUE)
@Slf4j
@ControllerAdvice
public class ServerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> globalException(Exception e){
        log.error("Unknow error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error("Unknow Error"));
    }
}
