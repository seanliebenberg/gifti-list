package org.giftilist.backend.wishlist;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
    private final WishlistService service;
    public WishlistController(WishlistService service) { this.service = service; }

    @GetMapping
    public List<WishlistItem> list() { return service.list(); }

    public record CreateWishlistItemRequest(
            @NotBlank(message = "title is required")
            String title,
            @Size(max = 1024)
            String url) {}

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public WishlistItem create(@Valid @RequestBody CreateWishlistItemRequest body) {
        return service.create(body.title(), body.url());
    }
}
