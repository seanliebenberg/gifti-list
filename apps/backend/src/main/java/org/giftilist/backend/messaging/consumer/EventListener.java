package org.giftilist.backend.messaging.consumer;

import org.giftilist.backend.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void handle(String message) {
        System.out.println("Received: " + message);
    }
}
