package com.example.demo.controller;

import com.example.demo.controller.request.CreateRoomRequest;
import com.example.demo.controller.request.JoinRoomRequest;
import com.example.demo.controller.response.CreateRoomResponse;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class ViewController {

    @Autowired
    private RoomService roomService;

    @PostMapping(value = "/createRoom")
    public CreateRoomResponse index(@RequestBody CreateRoomRequest createRoomRequest) {
        return roomService.createRoom(createRoomRequest);
    }

    @PostMapping(value = "/joinRoom")
    private Map<String,Object> joinRoom(@RequestBody JoinRoomRequest joinRoomRequest){
        return roomService.joinRoom(joinRoomRequest);
    }

}
