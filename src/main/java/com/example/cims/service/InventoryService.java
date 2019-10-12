package com.example.cims.service;

import com.example.cims.model.InventoryData;
import com.example.cims.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface InventoryService {
    ResponseEntity<Response> addNew(InventoryData inventoryData);

    ResponseEntity<Response> getInventory(String filter);

    ResponseEntity<Response> getInventoryItem(int id);

    ResponseEntity<Response> deleteInventoryItem(int id);

    ResponseEntity<Response> updateInventoryItem(int id, InventoryData newInvData);

    ResponseEntity<Response> searchInventoryItems(String keyword, String filter);

    ResponseEntity<Response> addnewInventory(InventoryData inventoryData, MultipartFile imageFile);
}
