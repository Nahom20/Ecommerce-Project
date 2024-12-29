package com.programmingtech.Inventory_service.Service;

import com.programmingtech.Inventory_service.Model.Inventory;
import com.programmingtech.Inventory_service.Repository.InventoryRepository;
import com.programmingtech.Inventory_service.dto.InventoryResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCodes) throws InterruptedException {
        System.out.println("wait started");
        Thread.sleep(10000);
        System.out.println("wait ended");


        return inventoryRepository.findBySkuCodeIn(skuCodes)
                .stream()
                .map(inventory -> {
                    InventoryResponse inventoryResponse= new InventoryResponse();
                    inventoryResponse.setSkuCode(inventory.getSkuCode());
                    inventoryResponse.setInStock(inventory.getQuantity() > 0);
                    System.out.println("Inventory: " + inventory.getSkuCode() + ", Quantity: " + inventory.getQuantity());
                    return inventoryResponse;
                })
                .toList();

    }
}
