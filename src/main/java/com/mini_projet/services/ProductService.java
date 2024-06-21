package com.mini_projet.services;

import com.mini_projet.entities.Product;
import com.mini_projet.entities.Store;
import com.mini_projet.entities.dto.request.ProductRequest;
import com.mini_projet.entities.dto.response.ProductResponse;
import com.mini_projet.mappers.ProductMapper;
import com.mini_projet.repositories.ProductRepository;
import com.mini_projet.repositories.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final StoreRepository storeRepository;


    public ProductResponse createProduct(ProductRequest request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));
        Product product = productMapper.toEntity(request);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }
}
