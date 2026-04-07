package org.giftilist.backend.wishlist.mapper;

import org.giftilist.backend.wishlist.WishlistItem;
import org.giftilist.backend.wishlist.api.WishlistItemResponse;

public final class WishlistItemMapper {
    private WishlistItemMapper() {}

    public static WishlistItemResponse toResponse(WishlistItem item) {
        return new WishlistItemResponse(
                item.getId(),
                item.getTitle(),
                item.getUrl()
        );
    }
}
