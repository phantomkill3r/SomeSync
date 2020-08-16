package com.example.demo.controller;

import com.example.demo.controller.request.OnStateChangeRequest;
import com.example.demo.controller.request.SyncRequest;
import com.example.demo.controller.request.UpdateUrlRequest;
import com.example.demo.service.RoomService;
import com.example.demo.service.SyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TriggerController {

    @Autowired
    private SyncService syncService;

    @Autowired
    private RoomService roomService;

    @PostMapping(value = "/updateState")
    public void test(@RequestBody OnStateChangeRequest request) {
        // check if the user is a host or not
        syncService.updateState(request);

    }

    @PostMapping(value = "/updateUrl")
    public void updateUrl(@RequestBody UpdateUrlRequest updateUrlRequest){
        syncService.updatevideoUrl(updateUrlRequest);
    }

    @PostMapping(value = "/sync")
    public void syncUrl(@RequestBody SyncRequest syncRequest){
        syncService.sync(syncRequest.getRoomId());
    }

}
