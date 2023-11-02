package com.macro.cloud.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class ChatServerApplication {
    public static void main(String[] args) {
//        SpringApplication.run(ChatServerApplication.class, args);

        try {
            ChatServerBootstrap bootstrap = new ChatServerBootstrap(9999);
            bootstrap.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("server socket 启动失败");
        }
    }
}
