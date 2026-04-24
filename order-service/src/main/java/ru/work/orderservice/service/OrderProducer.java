package ru.work.orderservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.work.orderservice.event.OrderCreatedEvent;


@Service
public class OrderProducer {

    public static final Logger log = LoggerFactory.getLogger(OrderProducer.class);

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    private final String ordersTopic;

    public OrderProducer(
            KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate,
            @Value("${app.kafka.topics.orders}") String ordersTopic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.ordersTopic = ordersTopic;
    }

    public void send(OrderCreatedEvent event){
        String key = event.orderId().toString();

        log.info("Sending order event to Kafka: topic={}, key={}, event={}",
                ordersTopic, key, event);

        kafkaTemplate.send(ordersTopic, key, event)
                .whenComplete((result, exception) -> {
                    if(exception != null) {
                        log.error("Failed to send order event: orderId={}", event.orderId(), exception);
                    } else {
                        log.info(
                                "Order event sent successfully: orderId={}, topic={}, partition={}, offset={}",
                                event.orderId(),
                                result.getRecordMetadata().topic(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset()
                        );
                    }
                });
    }
}
