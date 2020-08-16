package com.example.demo.service;

import com.example.demo.controller.request.CreateRoomRequest;
import com.example.demo.controller.request.JoinRoomRequest;
import com.example.demo.controller.request.UpdateUrlRequest;
import com.example.demo.controller.response.CreateRoomResponse;
import com.example.demo.constants.Constants;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.service.cache.MemCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomService {

    @Value("${roomUrlTemplate:http://localhost:8080/room/?}")
    private String createRoomUrl;

    @Autowired
    private MemCache memCache;

    public CreateRoomResponse createRoom(CreateRoomRequest request){
        User host = new User(request.getHostname(), request.getHostname(), true);
        Room room = new Room(host, new ArrayList(){{add(host);}}, Optional.empty());
        String roomId = UUID.randomUUID().toString();
        memCache.store(roomId, room);
        CreateRoomResponse response = new CreateRoomResponse(createRoomUrl(roomId), roomId);
        return response;
    }

    private String createRoomUrl(String roomId){
        return String.format(createRoomUrl,roomId);
    }

    public Map<String,Object> joinRoom(JoinRoomRequest request){
        Room room= (Room)memCache.get(request.getRoomId());
        User user = new User(request.getHostname(), request.getHostname(), false);
        room.getUserList().add(user);
        Map<String, Object> data = new HashMap<>();
        data.put("url", room.getCurrentUrl().isPresent() ? room.getCurrentUrl().get() : null);
        data.put("time",0);
        data.put("state", Constants.YTPlayer.PAUSED);
        return data;
    }

    public void updatevideoUrl(UpdateUrlRequest request){
        Room room = (Room) memCache.get(request.getRoomId());
        room.setCurrentUrl(Optional.of(request.getUrl()));
    }
}
