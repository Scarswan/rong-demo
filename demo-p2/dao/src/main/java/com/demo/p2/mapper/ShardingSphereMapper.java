package com.demo.p2.mapper;

import com.demo.model.shardingsphere.User;

import java.util.List;

public interface ShardingSphereMapper {

    Integer insert(String name);

    Integer update(User user);

    User getUser(int id);

    List<User> selectList();

}
