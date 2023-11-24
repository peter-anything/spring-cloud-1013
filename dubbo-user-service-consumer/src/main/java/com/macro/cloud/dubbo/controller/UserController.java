package com.macro.cloud.dubbo.controller;

import com.macro.cloud.dubbo.domain.entity.User;
import com.macro.cloud.dubbo.domain.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class UserController {
    @DubboReference(version = "1.0")
    private UserService userService;

    @GetMapping("/user/{id}")
    public User getUser(@PathParam("id") Long id) {
        System.out.println(id);
        System.out.println(userService);
        return userService.getUser(id);
    }
}
