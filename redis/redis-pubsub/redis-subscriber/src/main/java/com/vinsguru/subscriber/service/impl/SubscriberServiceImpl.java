package com.vinsguru.subscriber.service.impl;

import com.vinsguru.dto.Message;
import com.vinsguru.subscriber.service.SubscriberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberServiceImpl.class);

    @Autowired
    private ReactiveRedisOperations<String, Message> reactiveRedisTemplate;

    @Value("${topic.name:message}")
    private String topic;

    @Override
    public void subscribeTo(String userId){
        this.reactiveRedisTemplate
                .listenTo(ChannelTopic.of(topic + "-" + userId))
                .map(ReactiveSubscription.Message::getMessage)
                .subscribe(subscription -> LOGGER.info("Message received by userId {}", userId));
    }

}
