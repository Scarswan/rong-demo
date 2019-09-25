package com.demo.fallBack;

import com.demo.feign.Test2Client;
import com.demo.model.JsonResult;
import com.demo.model.test.User;
import org.springframework.stereotype.Component;

@Component
public class Test2ClientFallBack implements Test2Client {

    @Override
    public JsonResult test(User user) {
        return new JsonResult();
    }
}
