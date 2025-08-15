package org.giftilist.backend.wishlist;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishlistService {
    private final WishlistItemRepository repo;
    public WishlistService(WishlistItemRepository repo) { this.repo = repo; }
    public List<WishlistItem> list() { return repo.findAll(); }
    public WishlistItem create(String title, String url) { return repo.save(new WishlistItem(title, url)); }
}
