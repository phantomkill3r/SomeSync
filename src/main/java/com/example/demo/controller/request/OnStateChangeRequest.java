package com.example.demo.controller.request;

import lombok.Data;


@Data
public class OnStateChangeRequest {

    private String hostname;

    private String roomId;

    private int state;

    private double time;
}
