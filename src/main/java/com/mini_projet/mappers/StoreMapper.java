package com.mini_projet.mappers;

import com.mini_projet.entities.Store;
import com.mini_projet.entities.dto.request.StoreRequest;
import com.mini_projet.entities.dto.response.StoreResponse;
import org.springframework.stereotype.Service;

@Service
public class StoreMapper {
    public Store toEntity(StoreRequest request) {
        return new Store(
                null,
                request.getName()
        );
    }

    public StoreResponse toResponse(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getName()
        );
    }
}
