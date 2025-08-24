package org.giftilist.backend.wishlist;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
public class WishlistItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @Size(max = 1024)
    private String url;

    protected WishlistItem() {} // JPA
    public WishlistItem(String title, String url) { this.title = title; this.url = url; }
}
