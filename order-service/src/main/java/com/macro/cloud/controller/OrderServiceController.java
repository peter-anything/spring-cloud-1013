package com.macro.cloud.controller;

import com.macro.cloud.common.domain.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderServiceController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public Response<String> getUser(@PathVariable Long id) {
        Response<String> resp = Response.success("", "");
        String result="";
        for(int i=0;i<100;i++){
            result = restTemplate.getForObject("http://nacos-user-service/user/1", String.class);
            System.out.println(result);
        }
        return resp;
    }
}
