package com.example.demo.service;

import com.example.demo.client.PusherClient;
import com.example.demo.controller.request.OnStateChangeRequest;
import com.example.demo.controller.request.UpdateUrlRequest;
import com.example.demo.service.cache.MemCache;
import com.pusher.rest.Pusher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SyncService {

    @Autowired
    private MemCache memCache;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomservice;

    @Autowired
    private PusherClient pusherClient;

    public void updateState(OnStateChangeRequest request){
        // check if the user is a host
        boolean isHost = userService.isHost(request.getRoomId(), request.getHostname());
        if(!isHost) return;

        Map<String, Object> data = new HashMap<>();
        data.put("state", request.getState());
        data.put("time", request.getTime());

        pusherClient.trigger(request.getRoomId(), "updateState", data);
    }

    public void sync(String roomID){
        HashMap<String,String> data = new HashMap<>();
        pusherClient.trigger(roomID,"sync", data);
    }

    public void updatevideoUrl(UpdateUrlRequest request){
       roomservice.updatevideoUrl(request);
        Map<String,String> data = new HashMap<>();
        data.put("videoUrl", request.getUrl());
        pusherClient.trigger(request.getRoomId(), "updateVideoUrl", data);
        // broadCast the url
    }


}
