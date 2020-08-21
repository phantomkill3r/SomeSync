package com.example.demo.client;

import com.pusher.rest.Pusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PusherClient {

    private Pusher pusher;

    @Autowired
    PusherClient() {
        Pusher pusher = new Pusher("1054893", "b0c7f76e11d1d09b4d50", "accc11ba63b3764b26a1");
        pusher.setCluster("ap2");
        pusher.setEncrypted(true);
        this.pusher = pusher;
    }

    public void trigger(String channel, String event, Object data) {
        pusher.trigger(channel, event, data);
    }

    public String authenticate(String channel, String socketId) {
        return pusher.authenticate(socketId, channel);
    }

}
