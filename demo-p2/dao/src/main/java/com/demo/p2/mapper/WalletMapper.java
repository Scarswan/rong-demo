package com.demo.p2.mapper;

import com.demo.model.test.Wallet;

public interface WalletMapper {

    /**
     * 查询钱包数据
     *
     * @param userId
     * @return
     */
    Wallet selectById(Integer userId);

}
