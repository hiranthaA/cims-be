package com.example.cims.controller;

import com.example.cims.model.InventoryData;
import com.example.cims.model.Response;
import com.example.cims.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

     /**
      * add new inventory item
     */
    @RequestMapping(value = "/addnew", method= RequestMethod.POST)
    public ResponseEntity<Response> addnewItem(@RequestBody InventoryData inventoryData){
        return  inventoryService.addNew(inventoryData);
    }

    /**
     * get all inventory filter by type
     *
     * @Param filter Three types (all,cars,parts).
     *
     */
    @RequestMapping(value = "/get", method= RequestMethod.GET)
    public ResponseEntity<Response> getInventory(@RequestParam(required = true) String filter){
        return  inventoryService.getInventory(filter);
    }

    /**
     * get details of a specific inventory item
     *
     * @Param id Inventory item id
     */
    @RequestMapping(value = "/getitem", method= RequestMethod.GET)
    public ResponseEntity<Response> getInventoryItem(@RequestParam(required = true) int id){
        return  inventoryService.getInventoryItem(id);
    }

    /**
     * delete a specific inventory item
     *
     * @Param id Inventory item id
     */
    @RequestMapping(value = "/delete", method= RequestMethod.GET)
    public ResponseEntity<Response> deleteInventoryItem(@RequestParam(required = true) int id){
        return  inventoryService.deleteInventoryItem(id);
    }

    /**
     * update a specific inventory item
     *
     * @Param id Inventory item id
     */
    @RequestMapping(value = "/update", method= RequestMethod.POST)
    public ResponseEntity<Response> updateInventoryItem(@RequestParam(required = true) int id, @RequestBody InventoryData newInvData){
        return  inventoryService.updateInventoryItem(id,newInvData);
    }

}
