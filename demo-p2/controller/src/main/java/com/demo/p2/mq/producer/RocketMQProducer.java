package com.demo.p2.mq.producer;

import com.alibaba.fastjson.JSON;
import com.demo.model.rocketmq.RMQMessage;
import com.demo.p2.mapper.MQMessageMapper;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import java.util.Date;

@Scope("prototype")
public class RocketMQProducer implements IMqProducer {

    public static final int MAX_RETRY_COUNT = 10;

    @Value("demo_producer_group_rocketMQ")
    public String producerGroup;

    @Value("Tags")
    public String tags;

    @Value("127.0.0.1:9876")
    public String namesrvAddr;

    @Value("demo-mq-key")
    public String demoMqKey;

    private DefaultMQProducer defaultMQProducer;

    @Autowired
    private MQMessageMapper mqMessageMapper;

    @Override
    public void start(String producerGroup) throws MQClientException {
        this.producerGroup = producerGroup;

        defaultMQProducer = new DefaultMQProducer(producerGroup);
        defaultMQProducer.setNamesrvAddr(namesrvAddr);
        // defaultMQProducer.setRetryTimesWhenSendFailed(10);
        defaultMQProducer.start();
    }

    @Override
    public SendResult sendMsg(String topic, String msg) {
        try {
            System.err.println(String.format("rocketmq消息队列开始往主题%s发送消息%s", topic, msg));

            RMQMessage rmqMessage = new RMQMessage();
            Date date = new Date();
            rmqMessage.setCreateTime(date);
            rmqMessage.setUpdateTime(date);
            rmqMessage.setMProducerGroup(producerGroup);
            rmqMessage.setMTags(tags);
            rmqMessage.setMKeys(demoMqKey);
            rmqMessage.setIsDelete(0);
            rmqMessage.setIsTransaction(0);
            rmqMessage.setMBody(msg);
            rmqMessage.setMTopic(topic);
            rmqMessage.setMStatus("TO_SEND");

            mqMessageMapper.insertMsg(rmqMessage);

            Message message = new Message(topic, tags, msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
            message.putUserProperty(demoMqKey, JSON.toJSONString(rmqMessage));
            // "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h"
            // message.setDelayTimeLevel(3);

            SendResult sendResult = defaultMQProducer.send(message);

            int sendCount = 1;
            while (!sendResult.getSendStatus().name().equals(SendStatus.SEND_OK.name()) && sendCount < MAX_RETRY_COUNT) {
                sendResult = defaultMQProducer.send(message);
                sendCount++;
            }

            rmqMessage.setMaxRetryCount(MAX_RETRY_COUNT);
            rmqMessage.setSendCount(sendCount);
            rmqMessage.setSendResult(JSON.toJSONString(sendResult));
            rmqMessage.setMStatus(sendResult.getSendStatus().name());

            mqMessageMapper.updateByPrimaryKeySelective(rmqMessage);
            return sendResult;
        } catch (Exception e) {
            System.err.println("rocketmq消息队列往主题" + topic + "发送消息" + msg + "异常");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void shutDown() {
        if (defaultMQProducer != null)
            defaultMQProducer.shutdown();
    }

}
