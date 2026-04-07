package org.giftilist.backend.wishlist;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishlistItemService {
    private final WishlistItemRepository repo;

    public WishlistItemService(WishlistItemRepository repo) {
        this.repo = repo;
    }

    public List<WishlistItem> list() {
        return repo.findAll();
    }

    public WishlistItem create(String title, String url) {
        return repo.save(new WishlistItem(title, url));
    }

    public WishlistItem get(Long id) {
        return repo.findById(id).orElseThrow(() -> new WishlistItemNotFoundException(id));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new WishlistItemNotFoundException(id);
        }
        repo.deleteById(id);
    }
}
