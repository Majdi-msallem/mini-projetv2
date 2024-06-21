package com.mini_projet.controllers;

import com.mini_projet.entities.dto.request.StoreRequest;
import com.mini_projet.entities.dto.response.StoreResponse;
import com.mini_projet.services.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<StoreResponse> createStore(@RequestBody StoreRequest request) {
        StoreResponse createdStore = storeService.createStore(request);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StoreResponse>> getAllStores() {
        List<StoreResponse> stores = storeService.getAllStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }
}
