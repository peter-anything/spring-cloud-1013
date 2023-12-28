package com.macro.cloud.api;

import com.macro.cloud.domain.CommonResult;
import com.macro.cloud.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@Service
@FeignClient("nacos-user-service")
public interface UserServiceApi {
    @GetMapping("/user/{id}")
    CommonResult<User> getUser(@PathVariable("id") Long id);
}
