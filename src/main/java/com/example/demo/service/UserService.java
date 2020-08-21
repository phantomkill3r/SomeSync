package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.security.UserToken;
import com.example.demo.service.cache.MemCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserService {
    @Autowired
    private MemCache memCache;

    public boolean isHost(String roomId, String hostname){
        UserToken auth = (UserToken) SecurityContextHolder.getContext().getAuthentication();

        if(StringUtils.isBlank(roomId) || StringUtils.isBlank(hostname)) {
            log.error("roomId or hostanme cannot be blank");
            return false;
        }

        Room room = (Room)memCache.get(roomId);
        if(Objects.isNull(room)) {
            log.error("room does not exist");
            return false;
        }
        return room.getUserMap().get(auth.getId()).isHost();

    }

}
