package com.example.cims.service.Impl;

import com.example.cims.Entity.Favourite;
import com.example.cims.model.*;
import com.example.cims.repository.FavouriteRepository;
import com.example.cims.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Override
    public ResponseEntity<Response> addToFavourite(FavData favdata) {

        Response response = new Response();
        Favourite favourite = new Favourite();

        try{
            favourite.setInvid(favdata.getInv_id());
            favourite.setUserid(favdata.getUser_id());
            Favourite fav = favouriteRepository.findByUseridAndInvid(favdata.getUser_id(),favdata.getInv_id());
            if(fav==null){
                favouriteRepository.save(favourite);
                response.setMsg("Item Successfully Added to Favourites.");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
            else{
                response.setMsg("Item is already in favourites.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> removeFromFavourite(FavData favdata) {
        Response response = new Response();

        try{
            int returned = favouriteRepository.deleteFavourite(favdata.getUser_id(),favdata.getInv_id());
            if(returned==0){
                response.setMsg("No such item in favourites.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
            else{
                response.setMsg("Item successfully removed from favourites.");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        }
        catch(Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> getUserFavorites(int userid, String filter) {
        Response response = new Response();
        CarInventoryResult carInventoryResult;
        PartInventoryResult partInventoryResult;
        InventoryListFilter inventoryListFilter = new InventoryListFilter();
        List<CarInventoryResult> carInventoryResultList = new ArrayList<>();
        List<PartInventoryResult> partInventoryResultList = new ArrayList<>();

        try{
            if(filter.equals("cars")){
                List<Object[]> returned = favouriteRepository.getUserFavouriteCars(userid,"car");
                for(Object[] car : returned){
                    carInventoryResult = new CarInventoryResult((int)car[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[1]),(car[2]==null)? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[2]),(String)car[3],(int)car[4],(String)car[5],(int)car[6],(String)car[7],(String)car[8],(String)car[9],(int)car[10],(String)car[11],(int)car[12],(String)car[13],(String)car[14],(int)car[15],(int)car[16]);
                    carInventoryResultList.add(carInventoryResult);
                    inventoryListFilter.setCars(carInventoryResultList);

                }
            }
            else if(filter.equals("parts")){
                List<Object[]> returned = favouriteRepository.getUserFavouriteParts(userid,"part");
                for(Object[] part : returned){
                    partInventoryResult = new PartInventoryResult((int)part[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)part[1]),(part[2]==null)? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)part[2]),(String)part[3],(int)part[4],(String)part[5],(int)part[6],(String)part[7],(String)part[8],(String)part[9],(String)part[10],(int)part[11]);
                    partInventoryResultList.add(partInventoryResult);
                    inventoryListFilter.setParts(partInventoryResultList);
                }
            }
            else if(filter.equals("all")){
                List<Object[]> carsReturned = favouriteRepository.getUserFavouriteCars(userid,"car");
                for(Object[] car : carsReturned){
                    carInventoryResult = new CarInventoryResult((int)car[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[1]),(car[2]==null)? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[2]),(String)car[3],(int)car[4],(String)car[5],(int)car[6],(String)car[7],(String)car[8],(String)car[9],(int)car[10],(String)car[11],(int)car[12],(String)car[13],(String)car[14],(int)car[15],(int)car[16]);
                    carInventoryResultList.add(carInventoryResult);
                    inventoryListFilter.setCars(carInventoryResultList);

                }
                List<Object[]> partsReturned = favouriteRepository.getUserFavouriteParts(userid,"part");
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
            response.setMsg("Items successfully retrieved from favourites.");
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
