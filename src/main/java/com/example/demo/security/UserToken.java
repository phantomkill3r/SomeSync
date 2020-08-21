package com.example.demo.security;


import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserToken extends AbstractAuthenticationToken {

    private String id;

    private Set<String> authorities;

    public UserToken(String id, Set<String> authorities) {
        super(null);
        this.id = id;
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public Set<String> getAuths() {
        return this.authorities;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        auths = this.authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return auths;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}