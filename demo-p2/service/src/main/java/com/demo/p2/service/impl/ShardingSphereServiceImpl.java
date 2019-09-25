package com.demo.p2.service.impl;

import com.demo.feign.Test2Client;
import com.demo.model.JsonResult;
import com.demo.model.shardingsphere.User;
import com.demo.p2.mapper.ShardingSphereMapper;
import com.demo.p2.service.ShardingSphereService;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShardingSphereServiceImpl implements ShardingSphereService {

    @Autowired
    ShardingSphereMapper shardingSphereMapper;

    @Autowired
    Test2Client test2Client;

    @Override
    public Integer insert(String name) {
        return shardingSphereMapper.insert(name);
    }

    /**
     * 事务测试
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public JsonResult transactionalTest(User user) {
        // 设置事务类型
        TransactionTypeHolder.set(TransactionType.BASE);

        JsonResult jr = new JsonResult();
        Map<String, String> map = new HashMap<>();

        Integer rows = 0;
        try {
            System.err.println("开始了");
            rows = shardingSphereMapper.insert(user.getName());
            System.err.println("第一个结束了。。");
            map.put("row", String.valueOf(rows));
            jr.setMap(map);
            // user.setName(user.getName() + " test");
            if (rows > 0) {
                com.demo.model.test.User u = new com.demo.model.test.User();
                u.setUserId(1);
                u.setUserName(user.getName());


                System.err.println("B库没执行！");
                jr = test2Client.test(u);


                System.err.println("B库没问题！");
                // rows += shardingSphereMapper.update(user);
                rows += Integer.parseInt(jr.getMap().get("row"));
                map.put("row", String.valueOf(rows));
                jr.setMap(map);


                int n = 10 / 0;

                rows += shardingSphereMapper.update(user);
                map.put("row", String.valueOf(rows));
                jr.setMap(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("事务回滚！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return jr;
    }

    @Override
    public Integer update(User user) {
        return shardingSphereMapper.update(user);
    }

    @Override
    public User updateGet(User user) {
        User user1 = new User();
        Integer row = shardingSphereMapper.update(user);
        if (row > 0) {
            user1 = shardingSphereMapper.getUser(user.getId());
        }
        return user1;
    }

    @Override
    public User getOneUser(int id) {
        //HintManager hintManager = HintManager.getInstance();
        //hintManager.setMasterRouteOnly();
        User user = shardingSphereMapper.getUser(id);
        //hintManager.close();
        return user;
    }

    @Override
    public List<User> queryList() {
        return shardingSphereMapper.selectList();
    }


}
