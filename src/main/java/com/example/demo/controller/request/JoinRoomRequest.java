package com.example.demo.controller.request;

import lombok.Data;

@Data
public class JoinRoomRequest {
    private String hostname;
    private String roomId;
}
