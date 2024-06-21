package com.mini_projet.controllers;

import com.mini_projet.entities.dto.request.ProductRequest;
import com.mini_projet.entities.Store; // Importer l'entité Store si nécessaire
import com.mini_projet.repositories.ProductRepository;
import com.mini_projet.repositories.StoreRepository; // Importer le repository Store si nécessaire
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository; // Injecter le repository Store si nécessaire

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
        storeRepository.deleteAll(); // Supprimer tous les magasins avant chaque test
    }

    @Test
    public void testCreateProduct() throws Exception {
        // Given: Create a Store
        Store store = new Store(1L, "Store Description");
        storeRepository.save(store);

        // When: Perform POST request to create a product
        ProductRequest productRequest = new ProductRequest("Product 1", "Description 1", 100.0, store.getId());
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Product 1\",\"description\":\"Description 1\",\"price\":100.0,\"storeId\":" + store.getId() + "}"))
                // Then: Expect HTTP 201 Created and verify product JSON
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"name\":\"Product 1\",\"description\":\"Description 1\",\"price\":100.0,\"storeId\":" + store.getId() + "}"));
    }
}
