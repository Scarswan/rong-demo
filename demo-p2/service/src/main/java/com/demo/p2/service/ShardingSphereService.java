package com.demo.p2.service;

import com.demo.model.JsonResult;
import com.demo.model.shardingsphere.User;

import java.util.List;

public interface ShardingSphereService {

    Integer insert(String name);

    JsonResult transactionalTest(User user);

    Integer update(User user);

    User updateGet(User user);

    User getOneUser(int id);

    List<User> queryList();

}
