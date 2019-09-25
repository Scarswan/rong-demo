package com.demo.feign;

import com.demo.fallBack.Test2ClientFallBack;
import com.demo.model.JsonResult;
import com.demo.model.test.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "demo-c2",fallback = Test2ClientFallBack.class)
public interface Test2Client {

    @RequestMapping(value = "/test/update",method = RequestMethod.POST)
    JsonResult test(@RequestBody User user);

}
