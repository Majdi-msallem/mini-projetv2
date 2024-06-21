package com.mini_projet.services;


import com.mini_projet.entities.Store;
import com.mini_projet.entities.dto.request.StoreRequest;
import com.mini_projet.entities.dto.response.StoreResponse;
import com.mini_projet.mappers.StoreMapper;
import com.mini_projet.repositories.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public StoreResponse createStore(StoreRequest request) {
        Store store = storeMapper.toEntity(request);
        Store savedStore = storeRepository.save(store);
        return storeMapper.toResponse(savedStore);
    }

    public List<StoreResponse> getAllStores() {
        return storeRepository.findAll().stream()
                .map(storeMapper::toResponse)
                .collect(Collectors.toList());
    }
}
