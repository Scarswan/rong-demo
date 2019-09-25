package com.demo.p2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public IndexController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/index2")
    public String index2(){
        return "index2";
    }

}
