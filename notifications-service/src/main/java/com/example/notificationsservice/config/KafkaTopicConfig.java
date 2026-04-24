package com.example.notificationsservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic ordersDltTopic(
            @Value("${app.kafka.topics.orders-dlt}") String ordersDltTopic
    ) {
        return new NewTopic(ordersDltTopic, 3, (short) 1);
    }
}
