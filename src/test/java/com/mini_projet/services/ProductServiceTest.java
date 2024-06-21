package com.mini_projet.services;


import com.mini_projet.entities.Product;
import com.mini_projet.entities.Store;
import com.mini_projet.entities.dto.request.ProductRequest;
import com.mini_projet.entities.dto.response.ProductResponse;
import com.mini_projet.mappers.ProductMapper;
import com.mini_projet.repositories.ProductRepository;
import com.mini_projet.repositories.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;



    private ProductRequest productRequest;
    private Product productEntity;
    private ProductResponse productResponse;
    private Store store;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        store = new Store(1L, "Store 1");
        productRequest = new ProductRequest("Product 1", "Description 1", 100.0, store.getId());
        productEntity = new Product(1L, "Product 1", "Description 1", 100.0, store);
        productResponse = new ProductResponse(1L, "Product 1", "Description 1", 100.0, store.getId());

        when(storeRepository.findById(eq(store.getId()))).thenReturn(Optional.of(store));
        when(productMapper.toEntity(productRequest)).thenReturn(productEntity);
        when(productMapper.toResponse(productEntity)).thenReturn(productResponse);
        when(productRepository.save(any(Product.class))).thenReturn(productEntity);
    }

    @Test
    public void testCreateProduct() {
        ProductResponse result = productService.createProduct(productRequest);
        assertEquals(productResponse, result);
    }

    @Test
    public void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(productEntity));
        List<ProductResponse> products = productService.getAllProducts();
        assertEquals(Collections.singletonList(productResponse), products);
    }
}
