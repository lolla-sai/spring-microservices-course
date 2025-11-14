package com.courses.spring_boot_microservices.inventory.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.courses.spring_boot_microservices.inventory.model.Inventory;
import com.courses.spring_boot_microservices.inventory.repository.InventoryRepository;

@Component
public class SetupDBData implements CommandLineRunner {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started! Command-line arguments: " + String.join(", ", args));

        // Execute startup logic here
        Inventory obj = new Inventory(null, "BBMotaRawa", 100);
        inventoryRepository.save(obj);
    }
}