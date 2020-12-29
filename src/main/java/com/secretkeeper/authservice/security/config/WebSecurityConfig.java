package com.secretkeeper.authservice.security.config;

import com.secretkeeper.authservice.security.JwtAuthEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final LdapAuthenticationProvider authenticatorProvider;
    private final JwtAuthEntryPoint entryPoint;

    public WebSecurityConfig(LdapAuthenticationProvider authenticatorProvider,
                             JwtAuthEntryPoint entryPoint) {
        this.authenticatorProvider = authenticatorProvider;
        this.entryPoint = entryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticatorProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and().csrf().disable()
                .headers()
                .frameOptions()
                .deny()
                .and()
                .authorizeRequests().antMatchers("/auth").permitAll().
                        anyRequest().authenticated().and().
                        exceptionHandling().authenticationEntryPoint(entryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Add a filter to validate the tokens with every request
        //httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
