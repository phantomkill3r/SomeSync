package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
public class Room {
    private Map<String, User> userMap;
    private Optional<String> currentUrl;
}
