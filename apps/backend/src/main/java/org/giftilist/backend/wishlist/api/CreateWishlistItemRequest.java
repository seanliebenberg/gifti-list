package org.giftilist.backend.wishlist.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateWishlistItemRequest (
    @NotBlank(message = "title is required") String title,
    @Size(max = 1024, message = "url must be ≤ 1024 chars") String url
) {}
