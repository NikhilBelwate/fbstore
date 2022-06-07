package com.demo.fbstore.controller;

import com.demo.fbstore.service.MessageServiceMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageServiceMain messageServiceMain;

    @GetMapping("/showmsg")
    public String getMessage(){
        return messageServiceMain.getMainMessage();
    }
}
