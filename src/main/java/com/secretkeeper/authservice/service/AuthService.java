package com.secretkeeper.authservice.service;

import com.secretkeeper.authservice.controller.response.Jwt;
import com.secretkeeper.authservice.exception.BadUserCredentialException;
import com.secretkeeper.authservice.exception.UserDisabledException;
import com.secretkeeper.authservice.repository.UserEntity;
import com.secretkeeper.authservice.repository.UserRepository;
import com.secretkeeper.authservice.security.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    public Jwt authenticate(String username, String password){
        List<String> authorities = null;
        try {
            authorities = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password))
                    .getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        } catch (DisabledException e) {
            throw new UserDisabledException();
        } catch (BadCredentialsException e) {
            throw new BadUserCredentialException();
        }

        UserEntity user = userRepository.findByUsername(username);

        return new Jwt(jwtTokenUtil.generateToken(user,authorities));
    }

}
