package com.example.cims.service;

import com.example.cims.model.InventoryData;
import com.example.cims.model.Response;
import org.springframework.http.ResponseEntity;

public interface InventoryService {
    ResponseEntity<Response> addNew(InventoryData inventoryData);

    ResponseEntity<Response> getInventory(String filter);

    ResponseEntity<Response> getInventoryItem(int id);
}
