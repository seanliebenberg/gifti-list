package org.giftilist.backend.wishlist;

public class WishlistItemNotFoundException extends RuntimeException {
    public WishlistItemNotFoundException(Long id) {
        super("Wishlist item not found: " + id);
    }
}