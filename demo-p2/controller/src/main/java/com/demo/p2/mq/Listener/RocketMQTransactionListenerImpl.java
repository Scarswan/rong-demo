package com.demo.p2.mq.Listener;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;

public class RocketMQTransactionListenerImpl implements TransactionListener {

    // 存储对应事务的状态信息
    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    /**
     * 执行本地事务
     *
     * @param message
     * @param o
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        // 事务 id
        String transactionId = message.getTransactionId();

        localTrans.put(transactionId, 0);

        // 业务执行，处理本地事务，service
        System.err.println("hello!-Demo-Transaction");

        try {
            System.err.println("正在执行本地事务---");
            Thread.sleep(1000 * 60 + 10000);
            System.err.println("正在执行本地事务---成功！");
            localTrans.put(transactionId, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            localTrans.put(transactionId, 2);

            return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        return LocalTransactionState.COMMIT_MESSAGE;
    }

    /**
     * 消息回查
     *
     * @param messageExt
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        // 获取对应事务 id 的状态
        String transactionId = messageExt.getTransactionId();
        // 获取对应事务 id 的执行状态
        Integer status = localTrans.get(transactionId);

        System.err.println("消息回查-transactionId:" + transactionId + ",status" + status);

        switch (status) {
            case 0:
                return LocalTransactionState.UNKNOW;
            case 1:
                return LocalTransactionState.COMMIT_MESSAGE;
            case 2:
                return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        return LocalTransactionState.UNKNOW;
    }
}
