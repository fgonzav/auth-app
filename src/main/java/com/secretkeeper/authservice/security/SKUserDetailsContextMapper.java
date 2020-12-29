package com.secretkeeper.authservice.security;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component
public class SKUserDetailsContextMapper implements UserDetailsContextMapper {

    @Override
    @SneakyThrows
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {

        log.info("authorities " + authorities);
        return new User(username,new String((byte[]) ctx.getAttributes().get("userPassword").get()), authorities);
    }

    @Override
    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {

    }
}
