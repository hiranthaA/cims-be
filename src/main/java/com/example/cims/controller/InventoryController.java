package com.example.cims.controller;

import com.example.cims.model.InventoryData;
import com.example.cims.model.RegData;
import com.example.cims.model.Response;
import com.example.cims.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value = "/addnew", method= RequestMethod.POST)
    public ResponseEntity<Response> addnewItem(@RequestBody InventoryData inventoryData){
        return  inventoryService.addNew(inventoryData);
    }

}
