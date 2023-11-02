package com.macro.cloud.chat.client;

import com.alibaba.fastjson2.JSON;
import com.macro.cloud.chat.common.domain.ChatDto;

public class ChatClientApplication {
    public static void main(String[] args) {
        try {
            ChatClientBootstrap bootstrap = new ChatClientBootstrap(9999, "localhost");
/**
 *   发送消息
 */
            ChatDto dto=new ChatDto();
            dto.setClientId("123123").setMsg("test hello");
            /**
             * json字符串发送xx
             */
            bootstrap.socketChannel.writeAndFlush(JSON.toJSONString(dto));
            bootstrap.socketChannel.writeAndFlush(JSON.toJSONString(dto));
            bootstrap.socketChannel.writeAndFlush(JSON.toJSONString(dto));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("server socket 启动失败");
        }
    }
}
