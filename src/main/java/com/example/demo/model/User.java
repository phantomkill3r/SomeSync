package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class User {
    private String id;
    private String hostname;
    private String displayname;
    private boolean isHost;
    private Set<String> authorities;
}
