package com.example.demo.service;

import com.example.demo.controller.request.CreateRoomRequest;
import com.example.demo.controller.request.JoinRoomRequest;
import com.example.demo.controller.request.UpdateUrlRequest;
import com.example.demo.controller.response.CreateRoomResponse;
import com.example.demo.constants.Constants;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.security.UserToken;
import com.example.demo.service.cache.MemCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class RoomService {

    @Value("${roomUrlTemplate:http://localhost:8080/room/?}")
    private String createRoomUrl;

    @Autowired
    private MemCache memCache;

    public CreateRoomResponse createRoom(CreateRoomRequest request){
        UserToken auth = (UserToken)SecurityContextHolder.getContext().getAuthentication();
        User host = new User(auth.getId(), request.getHostname(), request.getHostname(), true, null);
        Room room = new Room(new HashMap<>(), Optional.empty());
        room.getUserMap().put(auth.getId(), host);
        String roomId = UUID.randomUUID().toString();
        memCache.store(roomId, room);
        CreateRoomResponse response = new CreateRoomResponse(createRoomUrl(roomId), roomId);
        return response;
    }

    private String createRoomUrl(String roomId){
        return String.format(createRoomUrl,roomId);
    }

    public Map<String,Object> joinRoom(JoinRoomRequest request){
        UserToken auth = (UserToken)SecurityContextHolder.getContext().getAuthentication();
        Room room = (Room)memCache.get(request.getRoomId());
        User user = new User(auth.getId(), request.getHostname(), request.getHostname(), false, null);
        if (room.getUserMap().get(auth.getId()) != null) {
            room.getUserMap().put(auth.getId(), user);
        }
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
