package com.secretkeeper.authservice.configuration;

import com.secretkeeper.authservice.configuration.properties.LdapProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfiguration {

    @Autowired
    private LdapProperties properties;

    @Bean
    public ContextSource contextSource(){
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setAnonymousReadOnly(true);
        contextSource.setUrl(properties.getUrl());
        contextSource.setBase(properties.getBase());
        contextSource.setUserDn(properties.getUserDn());
        contextSource.afterPropertiesSet();

        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate(ContextSource contextSource){
        return new LdapTemplate(contextSource);
    }
}
