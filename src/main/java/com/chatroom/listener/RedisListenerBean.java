package com.chatroom.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

public class RedisListenerBean {
    @Value("${server.port}")
    private String serverPort;

    @Value("${redis.channel.msgToAll}")
    private String msgToAll;

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // listen msgToAll
        container.addMessageListener(listenerAdapter, new PatternTopic(msgToAll));
        return container;
    }
}
