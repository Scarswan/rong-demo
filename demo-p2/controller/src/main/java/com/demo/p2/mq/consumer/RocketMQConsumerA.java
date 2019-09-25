package com.demo.p2.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.demo.model.rocketmq.RMQConsumer;
import com.demo.model.rocketmq.RMQMessage;
import com.demo.p2.mapper.MQConsumerMapper;
import com.demo.p2.utils.ConsumeStatusAppend;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import java.util.Date;
import java.util.List;

@Scope("prototype")
public class RocketMQConsumerA implements IMqConsumer {

    public static final int MAX_RETRY_COUNT = 10;

    @Value("Tags")
    public String tags;

    @Value("127.0.0.1:9876")
    public String namesrvAddr;

    @Value("demo-mq-key")
    public String demoMqKey;

    private DefaultMQPushConsumer consumer;

    @Autowired
    private MQConsumerMapper mqConsumerMapper;

    @Override
    public void start(String topic, String consumerGroup) throws Exception {
        System.err.println(String.format("%s消费者开始接收主题%s下消息", consumerGroup, topic));

        consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMax(5);
        consumer.setConsumeThreadMin(1);
        consumer.subscribe(topic, tags);
        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                try {
                    System.err.println(String.format("%s消费者接收主题%s下消息,条数：%d，", consumerGroup, topic, list.size()));

                    //单发消息不会出现多条的情况
                    for (int i = 0; i < list.size(); i++) {

                        RMQConsumer rmqConsumer = new RMQConsumer();
                        int consumeCount = 0;
                        try {
                            MessageExt messageExt = list.get(i);
                            //消息查重。。。。。。  //重复消费 则记录下重复消费情况  返回消费成功
                            RMQMessage rmqMessage = JSON.parseObject(messageExt.getProperty(demoMqKey), RMQMessage.class);

                            System.err.println(String.format("%s消费者处理主题%s下消息：%d  %s，", consumerGroup, topic, i, messageExt.getBody()));

                            Date date = new Date();
                            rmqConsumer.setCreateTime(date);
                            rmqConsumer.setUpdateTime(date);
                            rmqConsumer.setIsDelete(0);
                            rmqConsumer.setMConsumerGroup(consumerGroup);
                            rmqConsumer.setMTags(tags);
                            rmqConsumer.setMTopic(topic);
                            rmqConsumer.setCMID(rmqMessage.getMID());
                            rmqConsumer.setCMaxCount(MAX_RETRY_COUNT);

                            RMQConsumer selectConsume = new RMQConsumer();
                            selectConsume.setCMID(rmqMessage.getMID());
                            selectConsume.setMConsumerGroup(consumerGroup);
                            List<RMQConsumer> rmqConsumers = mqConsumerMapper.selectByMessage(selectConsume);
                            if (rmqConsumers != null) {
                                for (int j = 0; j < rmqConsumers.size(); j++) {
                                    if (ConsumeConcurrentlyStatus.CONSUME_SUCCESS.name().equals(rmqConsumers.get(j).getCStatus())) {
                                        rmqConsumer.setCStatus(ConsumeStatusAppend.CONSUME_CONSUMED.name());
                                        rmqConsumer.setCCount(rmqConsumers.size() + 1);
                                        mqConsumerMapper.insertSelective(rmqConsumer);
                                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                                    }
                                }
                                if (rmqConsumers.size() >= MAX_RETRY_COUNT) {
                                    //新增消费超过最大次数放弃
                                    rmqConsumer.setCStatus(ConsumeStatusAppend.CONSUME_REACH_MAX.name());
                                    rmqConsumer.setCCount(rmqConsumers.size());
                                    mqConsumerMapper.insertSelective(rmqConsumer);
                                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                                }
                            }

                            consumeCount = rmqConsumers == null ? 0 : rmqConsumers.size();


                            // TODO
                            //如果不重复
//                            String code = iConsumerListener.apply(new String(messageExt.getBody()));
//                            consumeCount++;
//                            if (RetCode.SUCC.getCode().equals(code)) {
//                                //新增消费成功记录
//                                rocketMqConsume.setConsumeStatus(ConsumeStatusAppend.CONSUME_SUCCESS.name());
//                                rocketMqConsume.setConsumeCount(consumeCount);
//                                rocketMqConsumeMapper.insertSelective(rocketMqConsume);
//                                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                            } else {
//
//                                //新增消费失败记录
//                                rocketMqConsume.setConsumeStatus(ConsumeStatusAppend.RECONSUME_LATER.name());
//                                rocketMqConsume.setConsumeCount(consumeCount);
//                                rocketMqConsumeMapper.insertSelective(rocketMqConsume);
//
//                                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//                            }
                        } catch (Exception e) {
                            //新增消费失败记录
                            rmqConsumer.setCStatus(ConsumeStatusAppend.RECONSUME_LATER.name());
                            rmqConsumer.setCCount(consumeCount);
                            mqConsumerMapper.insertSelective(rmqConsumer);

                            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

            }
        });
        consumer.start();
    }

    @Override
    public void shutDown() {
        if (consumer != null)
            consumer.shutdown();
    }

//    public void main() throws MQClientException {
//
//        consumer = new DefaultMQPushConsumer("demo_consumer_group_rocketMQ");
//        consumer.setNamesrvAddr("127.0.0.1:9876");
//        // consumer.setMessageModel(MessageModel.BROADCASTING);
//        consumer.setConsumeMessageBatchMaxSize(2);
//        consumer.subscribe("Topic_Demo", "*");
//        consumer.setMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                for (MessageExt msg : list) {
//                    try {
//                        String topic = msg.getTopic();
//                        String tags = msg.getTags();
//                        String result = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
//
//                        System.err.println("A-Consumer消费信息- topic:" + topic + ",tags:" + tags + ",result:" + result);
//
//                        // TODO 消费端消息持久化
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//
//                        int reconsumeTimes = msg.getReconsumeTimes();
//                        System.err.println("reconsumeTimes: " + reconsumeTimes);
//                        if (reconsumeTimes >= 10) {
//                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                        }
//                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//                    }
//                }
//
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//
//        consumer.start();
//        System.err.println("ConsumerA开启");
//
//    }

}
