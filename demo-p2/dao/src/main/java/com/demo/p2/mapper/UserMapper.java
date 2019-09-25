package com.demo.p2.mapper;

import com.demo.model.test.User;
import com.demo.model.test.VO.UserWalletVO;

import java.util.List;

public interface UserMapper {

    /**
     * 查询所有用户数据
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 完全删除
     *
     * @param userId
     * @return
     */
    Integer DeleteCompletely(Integer userId);

    /**
     * 普通删除(修改删除状态)
     *
     * @param userId
     * @return
     */
    Integer DeleteOrdinary(Integer userId);

    /**
     * 查询用户及其钱包数据
     *
     * @param userId
     * @return
     */
    UserWalletVO selectUWById(Integer userId);

}
