package com.courses.spring_boot_microservices.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courses.spring_boot_microservices.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Inventory findBySkuCodeAndQuantityNotEqualToZero(String skuCode);
    public boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);
}
