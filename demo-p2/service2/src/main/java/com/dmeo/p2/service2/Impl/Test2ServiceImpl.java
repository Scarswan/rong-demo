package com.dmeo.p2.service2.Impl;

import com.demo.model.test.User;
import com.demo.p2.mapper2.Test2Mapper;
import com.dmeo.p2.service2.Test2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Test2ServiceImpl implements Test2Service {

    @Autowired
    Test2Mapper test2Mapper;

    @Override
    public Integer update(User user) {
        return test2Mapper.update(user);
    }

}
