package com.demo.controller;

import com.demo.feign.Test2Client;
import com.demo.model.JsonResult;
import com.demo.model.test.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test2FeignController {

    @Autowired
    Test2Client test2Client;

    @RequestMapping("/update")
    public JsonResult test(@RequestBody User user) {
        return test2Client.test(user);
    }

}
