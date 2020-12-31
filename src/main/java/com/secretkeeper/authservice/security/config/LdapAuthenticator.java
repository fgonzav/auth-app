package com.secretkeeper.authservice.security.config;

import com.secretkeeper.authservice.configuration.properties.LdapProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

@Component
public class LdapAuthenticator{

    @Autowired
    private LdapProperties properties;

    @Bean
    public BindAuthenticator authenticator(BaseLdapPathContextSource contextSource) {
        BindAuthenticator authenticator = new BindAuthenticator(contextSource);
        authenticator.setUserDnPatterns(new String[] { "uid={0},"+properties.getUserDn() });
        return authenticator;
    }

    @Bean
    public LdapAuthoritiesPopulator authoritiesPopulator(ContextSource contextSource){
        return new DefaultLdapAuthoritiesPopulator(contextSource,"ou=Groups");
    }

    @Bean
    public LdapAuthenticationProvider authenticationProvider(org.springframework.security.ldap.authentication.LdapAuthenticator authenticator,
                                                             LdapAuthoritiesPopulator authoritiesPopulator,
                                                             UserDetailsContextMapper mapper) {
        LdapAuthenticationProvider provider = new LdapAuthenticationProvider(authenticator, authoritiesPopulator);
        provider.setUserDetailsContextMapper(mapper);
        return provider;
    }
}
