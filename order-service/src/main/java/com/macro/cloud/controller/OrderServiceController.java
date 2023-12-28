package com.macro.cloud.controller;

import com.macro.cloud.api.UserServiceApi;
import com.macro.cloud.common.domain.Response;
import com.macro.cloud.domain.CommonResult;
import com.macro.cloud.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderServiceController {
    @Autowired
    private UserServiceApi userServiceApi;

//    @Autowired
//    private RestTemplate restTemplate;


    @GetMapping("/{id}")
    public Response<String> getUser(@PathVariable Long id) {
        Response<String> resp = Response.success("", "");
        String result="";
        for(int i=0;i<100;i++){
            CommonResult<User>  user = userServiceApi.getUser(id);
            System.out.println(user.getData().getUsername());
//            String user = restTemplate.getForObject("http://nacos-user-service/user/1", String.class);
//            System.out.println(user);
        }
        return resp;
    }
}
