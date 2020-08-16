package com.example.demo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateRoomResponse {
    private String url;
    private String roomId;
}
