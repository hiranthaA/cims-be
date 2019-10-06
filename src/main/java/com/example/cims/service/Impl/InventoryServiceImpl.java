package com.example.cims.service.Impl;

import com.example.cims.Entity.Car;
import com.example.cims.Entity.Inventory;
import com.example.cims.Entity.Part;
import com.example.cims.model.*;
import com.example.cims.repository.CarRepository;
import com.example.cims.repository.InventoryRepository;
import com.example.cims.repository.PartRepository;
import com.example.cims.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            inventory.setAddedon(currDateTime);
            inventory.setExpon(inventoryData.getExp_on());
            inventory.setItemtype(inventoryData.getItem_type());
            inventory.setStock(inventoryData.getStock());

            Inventory res_inventory = inventoryRepository.save(inventory);

            if(inventoryData.getItem_type().equals("car")){
                car.setPlateno(inventoryData.getPlate_no());
                car.setBrand(inventoryData.getBrand());
                car.setModel(inventoryData.getModel());
                car.setProdyr(inventoryData.getProd_yr());
                car.setColor(inventoryData.getColor());
                car.setMileage(inventoryData.getMileage());
                car.setDescription(inventoryData.getDescription());
                car.setPrice(inventoryData.getPrice());
                car.setDownpayment(inventoryData.getDown_payment());
                car.setInvid(res_inventory.getInvid());
                carRepository.save(car);
                response.setMsg("Successfully added the new car to the inventory.");
            }
            else{
                part.setPartname(inventoryData.getPart_name());
                part.setBrand(inventoryData.getBrand());
                part.setDescription(inventoryData.getDescription());
                part.setPrice(inventoryData.getPrice());
                part.setInvid(res_inventory.getInvid());
                partRepository.save(part);
                response.setMsg("Successfully added the new part to the inventory.");
            }
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Response> getInventory(String filter) {
        Response response = new Response();
        CarInventoryResult carInventoryResult;
        PartInventoryResult partInventoryResult;
        InventoryFilter inventoryFilter = new InventoryFilter();
        List<CarInventoryResult> carInventoryResultList = new ArrayList<>();
        List<PartInventoryResult> partInventoryResultList = new ArrayList<>();

        try{
            if(filter.equals("cars")){
                List<Object[]> returned = inventoryRepository.getAllInventoryCars("car");
                for(Object[] car : returned){
                    carInventoryResult = new CarInventoryResult((int)car[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[1]),(car[2]==null)? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[2]),(String)car[3],(int)car[4],(int)car[5],(String)car[6],(String)car[7],(String)car[8],(int)car[9],(String)car[10],(int)car[11],(String)car[12],(String)car[13],(int)car[14],(int)car[15]);
                    carInventoryResultList.add(carInventoryResult);
                    inventoryFilter.setCars(carInventoryResultList);

                }
            }
            else if(filter.equals("parts")){
                List<Object[]> returned = inventoryRepository.getAllInventoryParts("part");
                for(Object[] part : returned){
                    partInventoryResult = new PartInventoryResult((int)part[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)part[1]),(part[2]==null)? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)part[2]),(String)part[3],(int)part[4],(int)part[5],(String)part[6],(String)part[7],(String)part[8],(String)part[9],(int)part[10]);
                    partInventoryResultList.add(partInventoryResult);
                    inventoryFilter.setParts(partInventoryResultList);
                }
            }
            else if(filter.equals("all")){
                List<Object[]> carsReturned = inventoryRepository.getAllInventoryCars("car");
                for(Object[] car : carsReturned){
                    carInventoryResult = new CarInventoryResult((int)car[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[1]),(car[2]==null)? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[2]),(String)car[3],(int)car[4],(int)car[5],(String)car[6],(String)car[7],(String)car[8],(int)car[9],(String)car[10],(int)car[11],(String)car[12],(String)car[13],(int)car[14],(int)car[15]);
                    carInventoryResultList.add(carInventoryResult);
                    inventoryFilter.setCars(carInventoryResultList);
                }
                List<Object[]> partsReturned = inventoryRepository.getAllInventoryParts("part");
                for(Object[] part : partsReturned){
                    partInventoryResult = new PartInventoryResult((int)part[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)part[1]),(part[2]==null)? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)part[2]),(String)part[3],(int)part[4],(int)part[5],(String)part[6],(String)part[7],(String)part[8],(String)part[9],(int)part[10]);
                    partInventoryResultList.add(partInventoryResult);
                    inventoryFilter.setParts(partInventoryResultList);
                }
            }
            else{
                response.setMsg("Sorry! Invalid Filter.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
            response.setData(inventoryFilter);
            response.setMsg("Items successfully retrieved from inventory.");
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
        catch(Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> getInventoryItem(int id) {
        Response response = new Response();
//        inventoryRepository.findByInv_id(25);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
