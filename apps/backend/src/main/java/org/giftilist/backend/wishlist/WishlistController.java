package org.giftilist.backend.wishlist;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
    private final WishlistService service;
    public WishlistController(WishlistService service) { this.service = service; }

    @GetMapping
    public List<WishlistItem> list() { return service.list(); }

    public record CreateWishlistItemRequest(String title, String url) {}

    @PostMapping
    public WishlistItem create(@RequestBody CreateWishlistItemRequest body) {
        return service.create(body.title(), body.url());
    }
}
