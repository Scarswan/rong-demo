package com.demo.controller2.controller;

import com.demo.model.JsonResult;
import com.demo.model.test.User;
import com.dmeo.p2.service2.Test2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class Test2Controller {

    @Autowired
    Test2Service test2Service;

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JsonResult test(@RequestBody User user) {
        JsonResult jr = new JsonResult();
        Map<String, String> map = new HashMap<>();

        Integer row = test2Service.update(user);
        map.put("row", String.valueOf(row));
        jr.setMap(map);
        if (row > 0) {
            jr.setStateCode(1);
        } else {
            jr.setStateCode(2);
        }

        return jr;
    }

}
