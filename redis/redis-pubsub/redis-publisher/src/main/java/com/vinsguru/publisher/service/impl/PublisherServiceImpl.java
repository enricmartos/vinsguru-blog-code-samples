package com.vinsguru.publisher.service.impl;

import com.vinsguru.dto.Message;
import com.vinsguru.publisher.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private ReactiveRedisOperations<String, Message> redisTemplate;

    @Value("${topic.name:message}")
    private String topic;

    @Override
    public void publishMessage(Message message, String userId) {
        Mono<Message> messageMono = Mono.just(message);
        messageMono
                .flatMap( m -> this.redisTemplate.convertAndSend(topic + "-" + userId, m))
                .subscribe();


    }

}
