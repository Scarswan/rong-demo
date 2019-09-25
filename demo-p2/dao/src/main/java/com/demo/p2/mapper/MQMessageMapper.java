package com.demo.p2.mapper;

import com.demo.model.rocketmq.RMQMessage;

public interface MQMessageMapper {

    Integer insertMsg(RMQMessage message);

    Integer updateByPrimaryKeySelective(RMQMessage message);

}
