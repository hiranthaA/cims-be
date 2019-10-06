package com.example.cims.service.Impl;

import com.example.cims.Entity.Car;
import com.example.cims.Entity.Inventory;
import com.example.cims.Entity.Part;
import com.example.cims.model.InventoryData;
import com.example.cims.model.Response;
import com.example.cims.repository.CarRepository;
import com.example.cims.repository.InventoryRepository;
import com.example.cims.repository.PartRepository;
import com.example.cims.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private PartRepository partRepository;

    @Override
    public ResponseEntity<Response> addNew(InventoryData inventoryData) {

        Response response = new Response();
        Inventory inventory = new Inventory();
        Car car = new Car();
        Part part = new Part();
        String currDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        try{
            inventory.setAdded_on(currDateTime);
            inventory.setExp_on(inventoryData.getExp_on());
            inventory.setItem_type(inventoryData.getItem_type());
            inventory.setStock(inventoryData.getStock());

            Inventory res_inventory = inventoryRepository.save(inventory);

            if(inventoryData.getItem_type().equals("car")){
                car.setPlate_no(inventoryData.getPlate_no());
                car.setBrand(inventoryData.getBrand());
                car.setModel(inventoryData.getModel());
                car.setProd_yr(inventoryData.getProd_yr());
                car.setColor(inventoryData.getColor());
                car.setMileage(inventoryData.getMileage());
                car.setDescription(inventoryData.getDescription());
                car.setPrice(inventoryData.getPrice());
                car.setDown_payment(inventoryData.getDown_payment());
                car.setInv_id(res_inventory.getInv_id());
                carRepository.save(car);
                response.setMsg("Successfully added the new car to the inventory.");
            }
            else{
                part.setPart_name(inventoryData.getPart_name());
                part.setBrand(inventoryData.getBrand());
                part.setDescription(inventoryData.getDescription());
                part.setPrice(inventoryData.getPrice());
                part.setInv_id(res_inventory.getInv_id());
                partRepository.save(part);
                response.setMsg("Successfully added the new part to the inventory.");
            }
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
