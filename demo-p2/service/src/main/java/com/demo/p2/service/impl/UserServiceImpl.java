package com.demo.p2.service.impl;

import com.demo.model.JsonResult;
import com.demo.model.test.User;
import com.demo.model.test.VO.UserWalletVO;
import com.demo.model.test.Wallet;
import com.demo.p2.mapper.UserMapper;
import com.demo.p2.mapper.WalletMapper;
import com.demo.p2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    WalletMapper walletMapper;

    @Override
    public List<User> queryAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public JsonResult deleteAppointUser(Integer userId, Integer completely) {
        JsonResult jr = new JsonResult();
        Map<String, String> map = new HashMap<>();
        Wallet wallet = walletMapper.selectById(userId);
        if (completely == 1) {
            userMapper.DeleteOrdinary(userId);
        } else if (completely == 2) {
            userMapper.DeleteCompletely(userId);
        }
        if (wallet.getWalletBalance().compareTo(BigDecimal.ZERO) > 0) {
            map.put("message", "用户余额大于0，请在用户余额为0时删除！");
            jr.setStateCode(40);
            jr.setMap(map);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return jr;
        }
        map.put("message", "用户已删除！");
        jr.setStateCode(20);
        jr.setMap(map);
        return jr;
    }

    @Override
    public UserWalletVO queryUAndW(Integer userId) {
        return userMapper.selectUWById(userId);
    }
}
