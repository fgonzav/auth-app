package com.secretkeeper.authservice.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class LdapAuthoritiesPopulator implements org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
        //DefaultAuthoritiesPopulator populator = new DefaultAuthoritiesPopulator();
        //userData.

        //ldapTemplate.se

        return null;
    }
}
