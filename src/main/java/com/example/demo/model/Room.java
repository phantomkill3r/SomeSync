package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class Room {
    private User host;
    private List<User> userList;
    private Optional<String> currentUrl;
}
