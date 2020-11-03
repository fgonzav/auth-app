package com.secretkeeper.authservice.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "ldap")
public class LdapProperties {

    private String url;
    private String base;
    private String userDn;
}
