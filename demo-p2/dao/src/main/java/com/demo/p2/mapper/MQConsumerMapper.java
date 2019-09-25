package com.demo.p2.mapper;

import com.demo.model.rocketmq.RMQConsumer;

import java.util.List;

public interface MQConsumerMapper {

    List<RMQConsumer> selectByMessage(RMQConsumer consumer);

    Integer insertSelective(RMQConsumer consumer);
}
