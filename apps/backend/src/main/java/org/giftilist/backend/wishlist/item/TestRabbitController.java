package org.giftilist.backend.wishlist.item;


import org.giftilist.backend.wishlist.messaging.publisher.EventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRabbitController {

    private final EventPublisher eventPublisher;

    public TestRabbitController(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/test-rabbit")
    public String testRabbit() {
        eventPublisher.publishItemReserved("item-123");
        return "published";
    }
}
