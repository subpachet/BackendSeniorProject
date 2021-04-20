package com.seniorproject.project.controller;

import com.seniorproject.project.model.AllFileResponse;
import com.seniorproject.project.model.AllUserResponse;
import com.seniorproject.project.model.FileStorage;
import com.seniorproject.project.model.User;
import com.seniorproject.project.model.responsemodel.ResponseModel;
import com.seniorproject.project.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
public class FileStorageController {
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/file/create")
    public ResponseEntity<ResponseModel> createFile(
            @RequestBody FileStorage request
    ){
        fileStorageService.createFile(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseModel(1000));
    }

    @GetMapping("/file/all")
    public ResponseEntity<ResponseModel<AllFileResponse>> getAllFiles(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseModel<AllFileResponse>(1000)
                        .setDataObj(fileStorageService.getAllFiles()));
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<ResponseModel<FileStorage>> getFileById(
            @PathVariable int id
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseModel<FileStorage>(1000)
                        .setDataObj(fileStorageService.getFileById(id)));
    }

    @DeleteMapping("/file/{id}")
    public ResponseEntity<ResponseModel<AllFileResponse>> deleteFileById(
            @PathVariable int id
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseModel<AllFileResponse>(1000)
                        .setDataObj(fileStorageService.deleteFileById(id)));
    }
}
