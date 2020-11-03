package com.secretkeeper.authservice.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredential {
    private String username;
    private String password;
}
