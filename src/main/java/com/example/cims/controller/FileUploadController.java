package com.example.cims.controller;

import com.example.cims.model.Response;
import com.example.cims.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * upload image
     */
    @RequestMapping(value = "/image", method= RequestMethod.POST)
    public ResponseEntity<Response> uploadImage(@RequestParam(required = true) MultipartFile imageFile){
            return fileUploadService.saveImage(imageFile);
    }

}
