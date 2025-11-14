package com.courses.spring_boot_microservices.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "InventoryService", url = "${inventory.service.url}")
public interface InventoryClient {
    @GetMapping("/api/inventory/check_product_with_quantity_exists")
    Boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
