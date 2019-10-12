package com.example.cims.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    ResponseEntity saveImage(MultipartFile imageFile);
}
