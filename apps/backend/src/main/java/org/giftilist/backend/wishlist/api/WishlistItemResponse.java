package org.giftilist.backend.wishlist.api;

public record WishlistItemResponse(
        Long id,
        String title,
        String url
) {}

