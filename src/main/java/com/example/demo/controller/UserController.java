package com.example.demo.controller;

import com.example.demo.controller.request.InitUserRequest;
import com.example.demo.controller.response.AuthResponse;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/auth")
    public AuthResponse authUser() {
        AuthResponse response = new AuthResponse();
        response.setToken(UUID.randomUUID().toString());
        log.info("UserController :: authUser :: " + response);
        return response;
    }

}
