package org.giftilist.backend.wishlist;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
    private final WishlistItemRepository repo;
    public WishlistController(WishlistItemRepository repo) { this.repo = repo; }

    @GetMapping
    public List<WishlistItem> list() { return repo.findAll(); }

    @PostMapping
    public WishlistItem create(@RequestBody WishlistItem body) {
        return repo.save(new WishlistItem(body.getTitle(), body.getUrl()));
    }
}
