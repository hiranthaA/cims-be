package com.example.cims.service;

import com.example.cims.model.FavData;
import com.example.cims.model.Response;
import org.springframework.http.ResponseEntity;

public interface FavouriteService {

    ResponseEntity<Response> addToFavourite(FavData favdata);

    ResponseEntity<Response> removeFromFavourite(FavData favdata);

    ResponseEntity<Response> getUserFavorites(int userid, String filter);
}
