package org.giftilist.backend.wishlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class WishlistControllerTest {

    private MockMvc mvc;
    private WishlistService service;

    @BeforeEach
    void setup() {
        service = Mockito.mock(WishlistService.class);
        when(service.list()).thenReturn(
                List.of(new WishlistItem("LEGO Classic", "https://example.com/lego"))
        );
        mvc = MockMvcBuilders.standaloneSetup(new WishlistController(service)).build();
    }

    @Test
    void listReturnsArray() throws Exception {
        mvc.perform(get("/api/wishlists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("LEGO Classic"));
    }
}
