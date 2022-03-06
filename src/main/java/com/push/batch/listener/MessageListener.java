package com.push.batch.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.redis.outbound.RedisQueueOutboundChannelAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    public static long COUNT = 1;
    public static long COUNT1 = 1;
    public static long COUNT2 = 1;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @ServiceActivator(inputChannel = "inputChannel", outputChannel = "redisChannel")
    public Message<?> receiveFromService(Message<?> message) throws InterruptedException {
        System.out.println("received from service : " + COUNT1++);
//        Thread.sleep(10000);
        return message;
    }

    @ServiceActivator(inputChannel = "redisChannel")
    public void sendMessageToQueue(Message<?> message) throws InterruptedException {
        RedisQueueOutboundChannelAdapter adapter = new RedisQueueOutboundChannelAdapter("Redis-Queue", jedisConnectionFactory);
        adapter.handleMessage(message);
        System.out.println("send to queue : " + COUNT2++);
//        Thread.sleep(10000);
    }

    @ServiceActivator(inputChannel = "receiverChannel")
    public void receiveFromQueue(Message<?> message) throws InterruptedException {
        System.out.println("received from redis queue ############## : " + COUNT++);
        System.out.println("received from redis queue ############## : " +  message);
        Thread.sleep(1000);
    }
}
