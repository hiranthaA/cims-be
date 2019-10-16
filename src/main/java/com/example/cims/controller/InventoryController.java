package com.example.cims.controller;

import com.example.cims.Entity.Inventory;
import com.example.cims.model.InventoryData;
import com.example.cims.model.Response;
import com.example.cims.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
     * add new inventory item with image upload
     */
    @RequestMapping(value = "/new", method= RequestMethod.POST)
    public ResponseEntity<Response> addnewInventory(
            @RequestParam(value = "imageFile", required = true) MultipartFile imageFile,
            @RequestParam(value = "item_type", required = true) String item_type,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "stock",required = false) Integer stock,
            @RequestParam(value = "plate_no",required = false) String plate_no,
            @RequestParam(value = "brand",required = false) String brand,
            @RequestParam(value = "model",required = false) String model,
            @RequestParam(value = "prod_yr",required = false) Integer prod_yr,
            @RequestParam(value = "color",required = false) String color,
            @RequestParam(value = "mileage",required = false) Integer mileage,
            @RequestParam(value = "price",required = false) Integer price,
            @RequestParam(value = "down_payment",required = false) Integer down_payment,
            @RequestParam(value = "part_name",required = false) String part_name,
            @RequestParam(value = "exp_on",required = false) String exp_on){
        InventoryData inventoryData = new InventoryData();
        inventoryData.setBrand(brand);
        inventoryData.setColor(color);
        inventoryData.setDescription(description);
        inventoryData.setDown_payment((down_payment==null)?0:down_payment);
        inventoryData.setExp_on(exp_on);
        inventoryData.setItem_type(item_type);
        inventoryData.setMileage((mileage==null)?0:mileage);
        inventoryData.setModel(model);
        inventoryData.setPart_name(part_name);
        inventoryData.setPlate_no(plate_no);
        inventoryData.setPrice((price==null)?0:price);
        inventoryData.setProd_yr((prod_yr==null)?0:prod_yr);
        inventoryData.setStock((stock==null)?0:stock);

        return  inventoryService.addnewInventory(inventoryData,imageFile);
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

    /**
     * update a specific inventory item with image upload
     *
     * @Param id Inventory item id
     */
    @RequestMapping(value = "/updateitem", method= RequestMethod.POST)
    public ResponseEntity<Response> updateInventoryItemWithImageUpload(
            @RequestParam(value = "invid", required = true) int invid,
            @RequestParam(value = "imageFile", required = true) MultipartFile imageFile,
            @RequestParam(value = "itemtype", required = true) String item_type,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "stock",required = false) Integer stock,
            @RequestParam(value = "plateno",required = false) String plate_no,
            @RequestParam(value = "brand",required = false) String brand,
            @RequestParam(value = "model",required = false) String model,
            @RequestParam(value = "prodyr",required = false) Integer prod_yr,
            @RequestParam(value = "color",required = false) String color,
            @RequestParam(value = "mileage",required = false) Integer mileage,
            @RequestParam(value = "price",required = false) Integer price,
            @RequestParam(value = "downpayment",required = false) Integer down_payment,
            @RequestParam(value = "partname",required = false) String part_name,
            @RequestParam(value = "expon",required = false) String exp_on){
        InventoryData inventoryData = new InventoryData();
        inventoryData.setBrand(brand);
        inventoryData.setColor(color);
        inventoryData.setDescription(description);
        inventoryData.setDown_payment((down_payment==null)?0:down_payment);
        inventoryData.setExp_on(exp_on);
        inventoryData.setItem_type(item_type);
        inventoryData.setMileage((mileage==null)?0:mileage);
        inventoryData.setModel(model);
        inventoryData.setPart_name(part_name);
        inventoryData.setPlate_no(plate_no);
        inventoryData.setPrice((price==null)?0:price);
        inventoryData.setProd_yr((prod_yr==null)?0:prod_yr);
        inventoryData.setStock((stock==null)?0:stock);
        return  inventoryService.updateInventoryItemWithImageUpload(invid,inventoryData,imageFile);
    }

    /**
     * search for inventory items
     *
     * @Param filter : (all,cars,parts)
     * @Param keyword : keyword searching for
     */
    @RequestMapping(value = "/search", method= RequestMethod.GET)
    public ResponseEntity<Response> searchInventoryItems(@RequestParam(required = true) String keyword, @RequestParam(required = true) String filter){
        return  inventoryService.searchInventoryItems(keyword,filter);
    }

}
