package com.example.cims.service;

import com.example.cims.model.Response;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    ResponseEntity<Response> saveImage(MultipartFile imageFile);
    String storeFile(MultipartFile file,String fileName);
    Resource loadFileAsResource(String fileName);
}
