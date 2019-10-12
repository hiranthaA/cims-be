package com.example.cims.service.Impl;

import com.example.cims.model.Response;
import com.example.cims.service.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public ResponseEntity<Response> saveImage(MultipartFile imageFile){
        Response response = new Response();
        try{
            byte[] bytes = imageFile.getBytes();
            Path absolutepath = Paths.get(".");
            Path path = Paths.get(absolutepath.toAbsolutePath()+"/src/main/webapp/uploads/" + "invno1"+System.currentTimeMillis()+imageFile.getOriginalFilename());
            Files.write(path,bytes);
            response.setMsg("File Uploaded Successfully.");
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
