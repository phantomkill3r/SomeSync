package com.example.demo.controllers;

import com.example.demo.requests.OnStateChangeRequest;
import com.pusher.rest.Pusher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TriggerController {

    @PostMapping(value = "/test")
    public void test(@RequestBody OnStateChangeRequest request) {
        Pusher pusher = new Pusher("1054893", "b0c7f76e11d1d09b4d50", "accc11ba63b3764b26a1");
        pusher.setCluster("ap2");
        pusher.setEncrypted(true);

        log.info("TriggerController :: test :: " + request);

        Map<String, Object> data = new HashMap<>();
        data.put("state", request.getState());
        data.put("time", request.getTime());

        pusher.trigger("my-channel", "my-event", data);
    }
}
