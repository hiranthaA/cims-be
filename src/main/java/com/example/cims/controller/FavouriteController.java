package com.example.cims.controller;

import com.example.cims.model.FavData;
import com.example.cims.model.RegData;
import com.example.cims.model.Response;
import com.example.cims.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favourite")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @RequestMapping(value = "/add", method= RequestMethod.POST)
    public ResponseEntity<Response> addToFav(@RequestBody FavData favdata){
        return  favouriteService.addToFavourite(favdata);
    }

    @RequestMapping(value = "/remove", method= RequestMethod.POST)
    public ResponseEntity<Response> removeFromFav(@RequestBody FavData favdata){
        return  favouriteService.removeFromFavourite(favdata);
    }

    @RequestMapping(value = "/getfav", method= RequestMethod.GET)
    public ResponseEntity<Response> getUserFavorites(@RequestParam(required = true) int userid, @RequestParam(required = false) String filter){
        return  favouriteService.getUserFavorites(userid, filter);
    }

}
