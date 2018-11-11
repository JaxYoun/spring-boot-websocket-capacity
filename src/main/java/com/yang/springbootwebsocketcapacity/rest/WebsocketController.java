package com.yang.springbootwebsocketcapacity.rest;

import com.yang.springbootwebsocketcapacity.config.WebsocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Yang
 * @date: 2018/11/11 17:41
 * @description:
 */
@RestController
@RequestMapping("/ws")
public class WebsocketController {

    @Autowired
    private WebsocketServer websocketServer;

    @GetMapping("/send")
    public void send() {
        this.websocketServer.send();
    }

}
