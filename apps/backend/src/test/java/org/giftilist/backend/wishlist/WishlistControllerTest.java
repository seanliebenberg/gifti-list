package org.giftilist.backend.wishlist;

import org.giftilist.backend.ApiExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class WishlistControllerTest {

    private MockMvc mvc;
    private WishlistService service;

    @BeforeEach
    void setup() {
        var validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();

        service = Mockito.mock(WishlistService.class);

        mvc = MockMvcBuilders.standaloneSetup(new WishlistController(service))
                .setControllerAdvice(new ApiExceptionHandler())
                .setValidator(validator)
                .build();
    }

    @Test
    void listReturnsArray() throws Exception {
        when(service.list()).thenReturn(List.of(
                new WishlistItem("LEGO Classic", "https://example.com/lego")
        ));

        mvc.perform(get("/api/wishlists"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("LEGO Classic"));
    }
    @Test
    void listForNonJSONReturnsNotAcceptable() throws Exception {
        mvc.perform(get("/api/wishlists").accept(MediaType.TEXT_HTML))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    void ListForMissingOrIncorrectContentTypeReturnsUnsupportedMediaType() throws Exception {
        mvc.perform(post("/api/wishlists")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("""
                            {"title":"Cookbook","url":"https://ex.com"}
                        """))
                .andExpect(status().isUnsupportedMediaType());
        verifyNoInteractions(service);
    }

    @Test
    void createEmptyWishlistReturnsBadRequest() throws Exception {
        mvc.perform(post("/api/wishlists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("validation failed"))
                .andExpect(jsonPath("$.fieldErrors.title").value("title is required"));
    }

    @Test
    void createWishlistReturns201AndLocation() throws Exception {
        var saved = new WishlistItem("Cookbook", "https://ex.com");
        // set an id appropriate since we do not have a db here:
         ReflectionTestUtils.setField(saved, "id", 42L);

        when(service.create("Cookbook", "https://ex.com")).thenReturn(saved);

        mvc.perform(post("/api/wishlists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {"title":"Cookbook","url":"https://ex.com"}
                        """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", org.hamcrest.Matchers.matchesPattern(".*/api/wishlists/\\d+")))
                .andExpect(jsonPath("$.id").value(42));
    }

    @Test
    void createWishlistReturnsCreated() throws Exception {
        when(service.create(any(),any())).thenReturn(
                new WishlistItem("Cookbook", "https://ex.com")
        );

        mvc.perform(post("/api/wishlists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {"title":"Cookbook","url":"https://ex.com"}
                        """))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Cookbook"))
                .andExpect(jsonPath("$.url").value("https://ex.com"));

        verify(service).create(any(String.class), any(String.class));
    }
}
