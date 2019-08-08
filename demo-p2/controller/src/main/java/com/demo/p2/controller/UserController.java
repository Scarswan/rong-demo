package com.demo.p2.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.JsonResult;
import com.demo.model.test.User;
import com.demo.model.test.VO.UserWalletVO;
import com.demo.p2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired(required = false)
    UserService userService;

    @RequestMapping(value = "/query/all", method = RequestMethod.POST)
    public List<User> queryAllUser() {
        return userService.queryAllUser();
    }

    @RequestMapping(value = "/delete/appoint", method = RequestMethod.POST)
    public JsonResult deleteAppointUser(@RequestBody String json) {
        JsonResult jr = userService.deleteAppointUser(JSONParser(json, "userId"), JSONParser(json, "completely"));
        return jr;
    }

    @RequestMapping(value = "/query/uaw", method = RequestMethod.POST)
    public UserWalletVO queryUAndW(@RequestBody String userId) {
        return userService.queryUAndW(JSONParser(userId, "userId"));
    }

    public Integer JSONParser(String str, String strname) {
        JSONObject jb = JSONObject.parseObject(str);
        return Integer.valueOf(jb.getString(strname));
    }

}
