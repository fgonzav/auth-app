package com.secretkeeper.authservice.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Jwt implements Serializable {

    private final String jwt;

    public Jwt(String jwt){
        this.jwt = jwt;
    }
}
