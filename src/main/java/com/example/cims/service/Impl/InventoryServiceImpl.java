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
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            inventory.setState("available");

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
    public ResponseEntity<Response> addnewInventory(InventoryData inventoryData, MultipartFile imageFile) {

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
            inventory.setState("available");

            Inventory res_inventory = inventoryRepository.save(inventory);

            //save image
            byte[] bytes = imageFile.getBytes();
            Path absolutepath = Paths.get(".");
            String image = "inv_"+res_inventory.getInvid()+"_"+System.currentTimeMillis()+"_"+imageFile.getOriginalFilename();
            Path path = Paths.get(absolutepath.toAbsolutePath()+"/src/main/webapp/uploads/" + image);
            Files.write(path,bytes);

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
                car.setPhoto(image);
                carRepository.save(car);
                response.setMsg("Successfully added the new car to the inventory.");
            }
            else{
                part.setPartname(inventoryData.getPart_name());
                part.setBrand(inventoryData.getBrand());
                part.setDescription(inventoryData.getDescription());
                part.setPrice(inventoryData.getPrice());
                part.setInvid(res_inventory.getInvid());
                part.setPhoto(image);
                partRepository.save(part);
                response.setMsg("Successfully added the new part to the inventory.");
            }
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Response> updateInventoryItemWithImageUpload(int id, InventoryData newInvData, MultipartFile imageFile) {
        Response response = new Response();
        Inventory oldInventory = new Inventory();
        Inventory updatedInventory = new Inventory();
        Car updatedCar = new Car();
        Car oldCar = new Car();
        Part oldPart = new Part();
        Part updatedPart = new Part();
        try{
            updatedInventory.setExpon(newInvData.getExp_on());
            updatedInventory.setStock(newInvData.getStock());
            updatedInventory.setState((newInvData.getStock()>0 ? "available" : "na"));
            updatedInventory.setInvid(id);

            //save image
            byte[] bytes = imageFile.getBytes();
            Path absolutepath = Paths.get(".");
            String image = "inv_"+id+"_"+System.currentTimeMillis()+"_"+imageFile.getOriginalFilename();
            Path path = Paths.get(absolutepath.toAbsolutePath()+"/src/main/webapp/uploads/" + image);
            Files.write(path,bytes);

            oldInventory = inventoryRepository.findByInvid(id);
            if(oldInventory==null){
                response.setMsg("Sorry! No such item in the inventory.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
            else if(oldInventory.getState().equals("deleted")){
                response.setMsg("Sorry! Item no longer available.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
            else if(oldInventory.getItemtype().equals("car")){
                updatedInventory.setAddedon(oldInventory.getAddedon());
                updatedInventory.setItemtype(oldInventory.getItemtype());

                oldCar = carRepository.findByInvid(id);
                updatedCar.setInvid(oldCar.getInvid());
                updatedCar.setCarid(oldCar.getCarid());
                updatedCar.setBrand(newInvData.getBrand());
                updatedCar.setColor(newInvData.getColor());
                updatedCar.setDescription(newInvData.getDescription());
                updatedCar.setDownpayment(newInvData.getDown_payment());
                updatedCar.setMileage(newInvData.getMileage());
                updatedCar.setModel(newInvData.getModel());
                updatedCar.setPlateno(newInvData.getPlate_no());
                updatedCar.setPrice(newInvData.getPrice());
                updatedCar.setProdyr(newInvData.getProd_yr());
                updatedCar.setPhoto(image);
                inventoryRepository.save(updatedInventory);
                carRepository.save(updatedCar);
                response.setMsg("Successfully updated the car.");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
            else if(oldInventory.getItemtype().equals("part")){
                updatedInventory.setAddedon(oldInventory.getAddedon());
                updatedInventory.setItemtype(oldInventory.getItemtype());

                oldPart = partRepository.findByInvid(id);
                updatedPart.setPartid(oldPart.getPartid());
                updatedPart.setInvid(oldPart.getInvid());
                updatedPart.setPartname(newInvData.getPart_name());
                updatedPart.setBrand(newInvData.getBrand());
                updatedPart.setDescription(newInvData.getDescription());
                updatedPart.setPrice(newInvData.getPrice());
                updatedPart.setPhoto(image);
                inventoryRepository.save(updatedInventory);
                partRepository.save(updatedPart);
                response.setMsg("Successfully updated the part.");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
            else{
                response.setMsg("Sorry! Something went wrong.");
                return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> getInventory(String filter) {
        Response response = new Response();
        CarInventoryResult carInventoryResult;
        PartInventoryResult partInventoryResult;
        InventoryListFilter inventoryListFilter = new InventoryListFilter();
        List<CarInventoryResult> carInventoryResultList = new ArrayList<>();
        List<PartInventoryResult> partInventoryResultList = new ArrayList<>();

        try{
            if(filter.equals("cars")){
                List<Object[]> returned = inventoryRepository.getAllInventoryCars("car");
                for(Object[] car : returned){
                    carInventoryResult = new CarInventoryResult((int)car[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[1]),(car[2]==null)? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[2]),(String)car[3],(int)car[4],(String)car[5],(int)car[6],(String)car[7],(String)car[8],(String)car[9],(int)car[10],(String)car[11],(int)car[12],(String)car[13],(String)car[14],(int)car[15],(int)car[16]);
                    carInventoryResultList.add(carInventoryResult);
                    inventoryListFilter.setCars(carInventoryResultList);

                }
            }
            else if(filter.equals("parts")){
                List<Object[]> returned = inventoryRepository.getAllInventoryParts("part");
                for(Object[] part : returned){
                    partInventoryResult = new PartInventoryResult((int)part[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)part[1]),(part[2]==null)? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)part[2]),(String)part[3],(int)part[4],(String)part[5],(int)part[6],(String)part[7],(String)part[8],(String)part[9],(String)part[10],(int)part[11]);
                    partInventoryResultList.add(partInventoryResult);
                    inventoryListFilter.setParts(partInventoryResultList);
                }
            }
            else if(filter.equals("all")){
                List<Object[]> carsReturned = inventoryRepository.getAllInventoryCars("car");
                for(Object[] car : carsReturned){
                    carInventoryResult = new CarInventoryResult((int)car[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[1]),(car[2]==null)? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[2]),(String)car[3],(int)car[4],(String)car[5],(int)car[6],(String)car[7],(String)car[8],(String)car[9],(int)car[10],(String)car[11],(int)car[12],(String)car[13],(String)car[14],(int)car[15],(int)car[16]);
                    carInventoryResultList.add(carInventoryResult);
                    inventoryListFilter.setCars(carInventoryResultList);
                }
                List<Object[]> partsReturned = inventoryRepository.getAllInventoryParts("part");
                for(Object[] part : partsReturned){
                    partInventoryResult = new PartInventoryResult((int)part[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)part[1]),(part[2]==null)? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)part[2]),(String)part[3],(int)part[4],(String)part[5],(int)part[6],(String)part[7],(String)part[8],(String)part[9],(String)part[10],(int)part[11]);
                    partInventoryResultList.add(partInventoryResult);
                    inventoryListFilter.setParts(partInventoryResultList);
                }
            }
            else{
                response.setMsg("Sorry! Invalid Filter.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
            response.setData(inventoryListFilter);
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
        CarInventoryResult carInventoryResult;
        PartInventoryResult partInventoryResult;
        InventoryItemFilter inventoryItemFilter = new InventoryItemFilter();

        try{
            Inventory item = inventoryRepository.findByInvid(id);
            if(item==null){
                response.setMsg("Sorry! Item not found.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
            else if(item.getItemtype().equals("car")){
                Object[] car = inventoryRepository.getInventoryCar("car",id).get(0);
                carInventoryResult = new CarInventoryResult((int)car[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[1]),(car[2]==null)? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[2]),(String)car[3],(int)car[4],(String)car[5],(int)car[6],(String)car[7],(String)car[8],(String)car[9],(int)car[10],(String)car[11],(int)car[12],(String)car[13],(String)car[14],(int)car[15],(int)car[16]);
                inventoryItemFilter.setCar(carInventoryResult);
                response.setData(inventoryItemFilter);
                response.setMsg("Inventory item found.");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
            else {
                Object[] part = inventoryRepository.getInventoryPart("part",id).get(0);
                partInventoryResult = new PartInventoryResult((int)part[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)part[1]),(part[2]==null)? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)part[2]),(String)part[3],(int)part[4],(String)part[5],(int)part[6],(String)part[7],(String)part[8],(String)part[9],(String)part[10],(int)part[11]);
                inventoryItemFilter.setPart(partInventoryResult);
                response.setData(inventoryItemFilter);
                response.setMsg("Inventory item found.");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        }
        catch (Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> deleteInventoryItem(int id) {
        Response response = new Response();
        try{
            Inventory item = inventoryRepository.findByInvid(id);
//            Inventory result = inventoryRepository.save()
            return null;
        }
        catch (Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> updateInventoryItem(int id, InventoryData newInvData) {
        Response response = new Response();
        Inventory oldInventory = new Inventory();
        Inventory updatedInventory = new Inventory();
        Car updatedCar = new Car();
        Car oldCar = new Car();
        Part oldPart = new Part();
        Part updatedPart = new Part();
        try{
            updatedInventory.setExpon(newInvData.getExp_on());
            updatedInventory.setStock(newInvData.getStock());
            updatedInventory.setState((newInvData.getStock()>0 ? "available" : "na"));
            updatedInventory.setInvid(id);

            oldInventory = inventoryRepository.findByInvid(id);
            if(oldInventory==null){
                response.setMsg("Sorry! No such item in the inventory.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
            else if(oldInventory.getState().equals("deleted")){
                response.setMsg("Sorry! Item no longer available.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
            else if(oldInventory.getItemtype().equals("car")){
                updatedInventory.setAddedon(oldInventory.getAddedon());
                updatedInventory.setItemtype(oldInventory.getItemtype());

                oldCar = carRepository.findByInvid(id);
                updatedCar.setInvid(oldCar.getInvid());
                updatedCar.setCarid(oldCar.getCarid());
                updatedCar.setBrand(newInvData.getBrand());
                updatedCar.setColor(newInvData.getColor());
                updatedCar.setDescription(newInvData.getDescription());
                updatedCar.setDownpayment(newInvData.getDown_payment());
                updatedCar.setMileage(newInvData.getMileage());
                updatedCar.setModel(newInvData.getModel());
                updatedCar.setPlateno(newInvData.getPlate_no());
                updatedCar.setPrice(newInvData.getPrice());
                updatedCar.setProdyr(newInvData.getProd_yr());
                //updatedCar.setPhoto(newInvData.getPhoto);
                inventoryRepository.save(updatedInventory);
                carRepository.save(updatedCar);
                response.setMsg("Successfully updated the car.");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
            else if(oldInventory.getItemtype().equals("part")){
                updatedInventory.setAddedon(oldInventory.getAddedon());
                updatedInventory.setItemtype(oldInventory.getItemtype());

                oldPart = partRepository.findByInvid(id);
                updatedPart.setPartid(oldPart.getPartid());
                updatedPart.setInvid(oldPart.getInvid());
                updatedPart.setPartname(newInvData.getPart_name());
                updatedPart.setBrand(newInvData.getBrand());
                updatedPart.setDescription(newInvData.getDescription());
                updatedPart.setPrice(newInvData.getPrice());
//                updatedPart.setPhoto(newInvData.getPhoto());
                inventoryRepository.save(updatedInventory);
                partRepository.save(updatedPart);
                response.setMsg("Successfully updated the part.");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
            else{
                response.setMsg("Sorry! Something went wrong.");
                return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> searchInventoryItems(String keyword, String filter) {
        Response response = new Response();
        List<CarInventoryResult> carInventoryResultsList = new ArrayList<>();
        List<PartInventoryResult> partInventoryResultsList = new ArrayList<>();
        InventoryListFilter inventoryListFilter = new InventoryListFilter();

        try{
            if(filter.equals("all")){
                List<Car> carlist = carRepository.findByBrandLikeOrModelLike("%"+keyword+"%","%"+keyword+"%");
                for(Car car : carlist){
                    Inventory inventory = inventoryRepository.findByInvid(car.getInvid());
                    if(inventory.getState().equals("deleted")){
                        break;
                    }
                    CarInventoryResult carInventoryResult = new CarInventoryResult(car.getInvid(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inventory.getAddedon()),inventory.getExpon()==null ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inventory.getExpon()),inventory.getItemtype(),inventory.getStock(),inventory.getState(),car.getCarid(),car.getPlateno(),car.getBrand(),car.getModel(),car.getProdyr(),car.getColor(),car.getMileage(),car.getPhoto(),car.getDescription(),car.getPrice(),car.getDownpayment());
                    carInventoryResultsList.add(carInventoryResult);
                }
                inventoryListFilter.setCars(carInventoryResultsList);
                List<Part> partlist = partRepository.findByBrandLikeOrPartnameLike("%"+keyword+"%","%"+keyword+"%");
                for(Part part : partlist){
                    Inventory inventory = inventoryRepository.findByInvid(part.getInvid());
                    if(inventory.getState().equals("deleted")){
                        break;
                    }
                    PartInventoryResult partInventoryResult = new PartInventoryResult(part.getInvid(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inventory.getAddedon()),inventory.getExpon()==null ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inventory.getExpon()),inventory.getItemtype(),inventory.getStock(),inventory.getState(),part.getPartid(),part.getPartname(),part.getBrand(),part.getPhoto(),part.getDescription(),part.getPrice());
                    partInventoryResultsList.add(partInventoryResult);
                }
                inventoryListFilter.setParts(partInventoryResultsList);
            }
            else if(filter.equals("cars")){
                List<Car> carlist = carRepository.findByBrandLikeOrModelLike("%"+keyword+"%","%"+keyword+"%");
                for(Car car : carlist){
                    Inventory inventory = inventoryRepository.findByInvid(car.getInvid());
                    if(inventory.getState().equals("deleted")){
                        break;
                    }
                    CarInventoryResult carInventoryResult = new CarInventoryResult(car.getInvid(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inventory.getAddedon()),inventory.getExpon()==null ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inventory.getExpon()),inventory.getItemtype(),inventory.getStock(),inventory.getState(),car.getCarid(),car.getPlateno(),car.getBrand(),car.getModel(),car.getProdyr(),car.getColor(),car.getMileage(),car.getPhoto(),car.getDescription(),car.getPrice(),car.getDownpayment());
                    carInventoryResultsList.add(carInventoryResult);
                }
                inventoryListFilter.setCars(carInventoryResultsList);
            }
            else if(filter.equals("parts")){
                List<Part> partlist = partRepository.findByBrandLikeOrPartnameLike("%"+keyword+"%","%"+keyword+"%");
                for(Part part : partlist){
                    Inventory inventory = inventoryRepository.findByInvid(part.getInvid());
                    if(inventory.getState().equals("deleted")){
                        break;
                    }
                    PartInventoryResult partInventoryResult = new PartInventoryResult(part.getInvid(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inventory.getAddedon()),inventory.getExpon()==null ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inventory.getExpon()),inventory.getItemtype(),inventory.getStock(),inventory.getState(),part.getPartid(),part.getPartname(),part.getBrand(),part.getPhoto(),part.getDescription(),part.getPrice());
                    partInventoryResultsList.add(partInventoryResult);
                }
                inventoryListFilter.setParts(partInventoryResultsList);
            }
            else{
                response.setMsg("Sorry! Invalid filter.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
            response.setData(inventoryListFilter);
            response.setMsg("Search successfully completed.");
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            response.setMsg("Sorry! Something went wrong.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
