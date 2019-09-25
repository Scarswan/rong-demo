package com.demo.p2.controller;

import com.demo.model.JsonResult;
import com.demo.model.shardingsphere.User;
import com.demo.p2.annotation.ShardingSphereMaster;
import com.demo.p2.service.ShardingSphereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shardingsphere")
public class ShardingSphereController {

    @Autowired
    ShardingSphereService shardingSphereService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody User user) {
        JsonResult jr = new JsonResult();
        Map<String, String> map = new HashMap<>();

        Integer row = shardingSphereService.insert(user.getName());
        map.put("row", String.valueOf(row));
        jr.setMap(map);
        if (row > 0) {
            jr.setStateCode(1);
        } else {
            jr.setStateCode(2);
        }

        return jr;
    }

    @RequestMapping(value = "/transactional/test", method = RequestMethod.POST)
    public JsonResult transactionalTest(@RequestBody User user) {
        JsonResult jr = new JsonResult();
        Map<String, String> map = new HashMap<>();

        jr = shardingSphereService.transactionalTest(user);
//        map.put("rows", String.valueOf(rows));
//        jr.setMap(map);
//        if (rows > 0) {
//            jr.setStateCode(1);
//        } else {
//            jr.setStateCode(2);
//        }

        return jr;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody User user) {
        JsonResult jr = new JsonResult();
        Map<String, String> map = new HashMap<>();

        Integer row = shardingSphereService.update(user);
        map.put("row", String.valueOf(row));
        jr.setMap(map);
        if (row > 0) {
            jr.setStateCode(1);
        } else {
            jr.setStateCode(2);
        }
        return jr;
    }


    @ShardingSphereMaster
    @RequestMapping(value = "/update/get", method = RequestMethod.POST)
    public User updateGet(@RequestBody User user) {
        return shardingSphereService.updateGet(user);
    }

    @ShardingSphereMaster
    @RequestMapping("/query/get/{id}")
    public User getUser(@PathVariable("id") int id) {
        User user = shardingSphereService.getOneUser(id);
        return user;
    }

    @RequestMapping("/query/list")
    public List<User> getList() {
        return shardingSphereService.queryList();
    }


}
