package org.giftilist.backend.Wishlist;

import org.giftilist.backend.wishlist.WishlistItem;
import org.giftilist.backend.wishlist.WishlistItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
class WishlistRepoIT {

    static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("giftilist").withUsername("postgres").withPassword("postgres");
    static { db.start(); }

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url", db::getJdbcUrl);
        r.add("spring.datasource.username", db::getUsername);
        r.add("spring.datasource.password", db::getPassword);
    }

    @Autowired
    WishlistItemRepository repo;

    @Test void savesAndReads() {
        var saved = repo.save(new WishlistItem("Cookbook","https://ex.com/cookbook"));
        assertThat(saved.getId()).isNotNull();
        assertThat(repo.findAll()).extracting(WishlistItem::getTitle).contains("Cookbook");
    }
}
