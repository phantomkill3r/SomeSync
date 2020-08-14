package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ViewController {

    @RequestMapping(value = "/room")
    public String index() {
        return "index";
    }
}
