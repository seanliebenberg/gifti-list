package org.giftilist.backend.wishlist.messaging.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public EventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishItemReserved(String itemId) {
        rabbitTemplate.convertAndSend("gift-events", "ItemReserved:" + itemId);
    }
}
