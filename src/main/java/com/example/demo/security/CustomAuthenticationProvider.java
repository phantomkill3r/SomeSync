package com.example.demo.security;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserToken token = (UserToken) authentication;

        String id = token.getId();

        return new UserToken(id, token.getAuths());
    }
 
    @Override
    public boolean supports( Class<?> authentication ) {
        return authentication.isAssignableFrom(UserToken.class);
    }

}