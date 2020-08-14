package com.example.demo.requests;

import com.example.demo.constants.Constants.*;
import lombok.Data;


@Data
public class OnStateChangeRequest {

    private int state;

    private double time;
}
