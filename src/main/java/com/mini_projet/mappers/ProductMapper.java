package com.mini_projet.mappers;

import com.mini_projet.entities.Product;
import com.mini_projet.entities.Store;
import com.mini_projet.entities.dto.request.ProductRequest;
import com.mini_projet.entities.dto.response.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toEntity(ProductRequest request) {
        Store store = new Store();
        store.setId(request.getStoreId());
        return new Product(
                null,
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                store
        );
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                //product.getStoreId()
                product.getStore().getId()
        );
    }
}
