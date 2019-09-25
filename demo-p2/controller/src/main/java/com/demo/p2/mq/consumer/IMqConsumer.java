package com.demo.p2.mq.consumer;

public interface IMqConsumer {

    void start(String topic, String consumerGroup) throws Exception;

    void shutDown();

}
