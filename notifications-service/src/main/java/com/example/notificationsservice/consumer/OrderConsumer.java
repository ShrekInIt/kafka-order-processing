package com.example.notificationsservice.consumer;

import com.example.notificationsservice.event.OrderCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {
    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(
            topics = "${app.kafka.topics.orders}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(
            ConsumerRecord<String, OrderCreatedEvent> record,
            Acknowledgment acknowledgment
    ) {
        OrderCreatedEvent event = record.value();

        log.info(
                "Order received: orderId={}, userId={}, productCode={}, quantity={}, thread={}, partition={}, offset={}",
                event.orderId(),
                event.userId(),
                event.productCode(),
                event.quantity(),
                Thread.currentThread().getName(),
                record.partition(),
                record.offset()
        );

        if (event.quantity() > 100) {
            log.error("Order quantity is too large. Sending to retry flow: orderId={}, quantity={}",
                    event.orderId(), event.quantity());

            throw new IllegalArgumentException("Quantity must be <= 100");
        }

        log.info("Notification sent successfully for orderId={}", event.orderId());

        acknowledgment.acknowledge();
    }
}
