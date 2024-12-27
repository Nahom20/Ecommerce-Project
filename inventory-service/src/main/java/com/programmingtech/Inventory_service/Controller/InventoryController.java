package com.programmingtech.Inventory_service.Controller;

import com.programmingtech.Inventory_service.Service.InventoryService;
import com.programmingtech.Inventory_service.dto.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> isInStock(@RequestParam("skuCodes") List<String> skuCodes){
        List<InventoryResponse> inventoryResponses = inventoryService.isInStock(skuCodes);
        return new ResponseEntity<>(inventoryResponses, HttpStatus.ACCEPTED);
    }


}
