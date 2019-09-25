package com.demo.p2.mq.producer;

import com.demo.p2.mq.Listener.RocketMQTransactionListenerImpl;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;

public class RocketMQTransactionProducer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException,
            InterruptedException, MQBrokerException {
        // 1.创建 TransactionMQProducer
        TransactionMQProducer producer = new TransactionMQProducer("demo_producer_group_rocketMQ");

        // 2.设置 Namesrv 地址
        producer.setNamesrvAddr("127.0.0.1:9876");

        // 指定消息监听对象，用于执行本地事务和消息回查
        TransactionListener transactionListener = new RocketMQTransactionListenerImpl();
        producer.setTransactionListener(transactionListener);

        // 线程池
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(200), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        }
        );
        producer.setExecutorService(executorService);

        // 3.开启 TransactionMQProducer
        producer.start();

        // 5.发送消息
        // 4.创建消息Messsage
        Message message = new Message("Topic_Demo", "Tags", "Keys_T",
                "hello!-Transaction".getBytes(RemotingHelper.DEFAULT_CHARSET));

        // 发送事务消息
        TransactionSendResult result = producer.sendMessageInTransaction(message, "hello-transaction");
        System.err.println(result);

        // 6.关闭 TransactionMQProducer
        producer.shutdown();
    }

}
