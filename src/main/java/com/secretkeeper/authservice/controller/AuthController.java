package com.secretkeeper.authservice.controller;

import com.secretkeeper.authservice.controller.request.UserCredential;
import com.secretkeeper.authservice.controller.response.Jwt;
import com.secretkeeper.authservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Jwt> login(@RequestBody UserCredential credentials){
        log.info("Request received");
        ResponseEntity<Jwt> response = ResponseEntity.ok(service.authenticate(credentials.getUsername(),credentials.getPassword()));
        log.info("Response ok");
        return response;
    }
}
