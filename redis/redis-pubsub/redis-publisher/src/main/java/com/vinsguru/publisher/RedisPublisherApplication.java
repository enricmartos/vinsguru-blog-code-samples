package com.vinsguru.publisher;

import com.vinsguru.dto.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RedisPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisPublisherApplication.class, args);
    }

    @Bean
    public ReactiveRedisOperations<String, Message> jokeTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        RedisSerializer<Message> valueSerializer = new Jackson2JsonRedisSerializer<>(Message.class);
        RedisSerializationContext<String, Message> serializationContext = RedisSerializationContext.<String, Message>newSerializationContext(RedisSerializer.string())
                .value(valueSerializer)
                .build();
        return new ReactiveRedisTemplate<>(lettuceConnectionFactory, serializationContext);
    }
}
