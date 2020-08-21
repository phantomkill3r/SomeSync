package com.example.demo.controller.request;

import lombok.Data;

@Data
public class InitUserRequest {

    private String channel;

    private String socketId;
}
