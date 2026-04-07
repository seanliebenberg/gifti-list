package org.giftilist.backend.wishlist;

import jakarta.validation.Valid;
import org.giftilist.backend.wishlist.api.CreateWishlistItemRequest;
import org.giftilist.backend.wishlist.api.WishlistItemResponse;
import org.giftilist.backend.wishlist.mapper.WishlistItemMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static org.giftilist.backend.wishlist.mapper.WishlistItemMapper.toResponse;

@RestController
@RequestMapping(value = "/api/wishlist-items", produces = MediaType.APPLICATION_JSON_VALUE)
public class WishlistItemController {
    private final WishlistItemService service;
    public WishlistItemController(WishlistItemService service) { this.service = service; }

    @GetMapping
    public List<WishlistItemResponse> list() {
        return service.list()
                .stream()
                .map(WishlistItemMapper::toResponse)
                .toList();
    }

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

    @GetMapping("/{id}")
    public WishlistItemResponse get(@PathVariable Long id) {
        return toResponse(service.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
