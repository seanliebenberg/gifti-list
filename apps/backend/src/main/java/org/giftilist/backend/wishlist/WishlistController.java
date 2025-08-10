package org.giftilist.backend.wishlist;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
    @GetMapping
    public List<WishlistItemDto> list() {
        return List.of(
                new WishlistItemDto(1L, "LEGO Classic", "https://example.com/lego"),
                new WishlistItemDto(2L, "Cookbook", "https://example.com/cookbook")
        );
    }
}
