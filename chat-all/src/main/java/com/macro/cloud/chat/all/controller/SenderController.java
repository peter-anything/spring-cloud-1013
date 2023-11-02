package com.macro.cloud.chat.all.controller;

import com.macro.cloud.chat.all.client.ChatClient;
import com.macro.cloud.chat.all.protocol.protobuf.MessageBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SenderController {
    @Autowired
    private ChatClient nettyClient;

    @GetMapping("/send")
    public String send(String content) {
        MessageBase.Message message = MessageBase.Message.newBuilder().setCmd(MessageBase.Message.CommandType.NORMAL)
                .setContent(content)
                .setRequestId(UUID.randomUUID().toString()).build();
        nettyClient.sendMsg(message);
        return "send ok";
    }
}
