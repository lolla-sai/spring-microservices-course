package com.courses.spring_boot_microservices.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.courses.spring_boot_microservices.inventory.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/health")
    public String healthCheck() {
        return "Inventory Service is up and running!";
    }

    @GetMapping("/check_product_with_quantity_exists")
    public Boolean checkProductWithQuantityExists(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }

}
