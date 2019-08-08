package com.demo.controller;

import com.demo.feign.DemoProviderInterface;
import com.demo.model.JsonResult;
import com.demo.model.test.User;
import com.demo.model.test.VO.UserWalletVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    DemoProviderInterface demoProviderInterface;

    @RequestMapping(value = "/query/all", method = RequestMethod.POST)
    public List<User> queryAllUser() {
        return demoProviderInterface.queryAllUser();
    }

    @RequestMapping(value = "/delete/appoint", method = RequestMethod.POST)
    public JsonResult deleteAppointUser(@RequestBody String json) {
        return demoProviderInterface.deleteAppointUser(json);
    }

    @RequestMapping(value = "/query/uaw", method = RequestMethod.POST)
    public UserWalletVO queryUAndW(@RequestBody Integer userId) {
        return demoProviderInterface.queryUAndW(userId);
    }

}
