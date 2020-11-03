package com.secretkeeper.authservice.service;

import com.secretkeeper.authservice.controller.response.Jwt;
import com.secretkeeper.authservice.exception.BadUserCredentialException;
import com.secretkeeper.authservice.exception.UserDisabledException;
import com.secretkeeper.authservice.repository.UserEntity;
import com.secretkeeper.authservice.repository.UserRepository;
import com.secretkeeper.authservice.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    public Jwt authenticate(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new UserDisabledException();
        } catch (BadCredentialsException e) {
            throw new BadUserCredentialException();
        }

        UserEntity user = userRepository.findByUsername(username);

        return new Jwt(jwtTokenUtil.generateToken(user));
    }

}
