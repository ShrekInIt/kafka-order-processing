package ru.work.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import ru.work.orderservice.event.OrderCreatedEvent;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic ordersTopic(
            @Value("${app.kafka.topics.orders}") String orderTopic
    ){
        return new NewTopic(orderTopic, 3, (short) 1);
    }

    @Bean
    public KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate(
            ProducerFactory<String, OrderCreatedEvent> producerFactory
    ) {
        return new KafkaTemplate<>(producerFactory);
    }
}
