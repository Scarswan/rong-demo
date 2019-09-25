package com.demo.p2.service;

import com.demo.model.JsonResult;
import com.demo.model.test.User;
import com.demo.model.test.VO.UserWalletVO;

import java.util.List;

public interface UserService {

    /**
     * 查询所有用户数据
     *
     * @return
     */
    List<User> queryAllUser();

    /**
     * 删除指定用户
     *
     * @param userId 用户id
     * @return
     */
    JsonResult deleteAppointUser(Integer userId, Integer completely);

    /**
     * 查询指定用户及其钱包数据
     *
     * @param userId
     * @return
     */
    UserWalletVO queryUAndW(Integer userId);

}
