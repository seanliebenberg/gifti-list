package org.giftilist.backend.wishlist;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class WishlistItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String url;

    protected WishlistItem() {} // JPA
    public WishlistItem(String title, String url) { this.title = title; this.url = url; }
}
