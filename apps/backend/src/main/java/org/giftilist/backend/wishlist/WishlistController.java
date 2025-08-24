package org.giftilist.backend.wishlist;

import jakarta.validation.Valid;
import org.giftilist.backend.wishlist.api.CreateWishlistItemRequest;
import org.giftilist.backend.wishlist.api.WishlistItemResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api/wishlists", produces = MediaType.APPLICATION_JSON_VALUE)
public class WishlistController {
    private final WishlistService service;
    public WishlistController(WishlistService service) { this.service = service; }

    @GetMapping
    public List<WishlistItemResponse> list() {
        return service.list().stream().map(this::toResponse).toList(); }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WishlistItemResponse> create(@Valid @RequestBody CreateWishlistItemRequest body) {
        var saved = service.create(body.title(), body.url());
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(toResponse(saved));
    }

    private WishlistItemResponse toResponse(WishlistItem it) {
        return new WishlistItemResponse(it.getId(), it.getTitle(), it.getUrl());
    }
}
