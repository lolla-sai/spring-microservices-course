package com.courses.spring_boot_microservices.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.spring_boot_microservices.inventory.repository.InventoryRepository;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public Boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }

}
