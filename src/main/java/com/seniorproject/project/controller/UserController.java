package com.seniorproject.project.controller;

import com.seniorproject.project.model.AllUserResponse;
import com.seniorproject.project.model.User;
import com.seniorproject.project.model.responsemodel.ResponseModel;
import com.seniorproject.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.StringJoiner;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    public ResponseEntity<ResponseModel<User>> createUser(
            @RequestBody User request
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseModel<User>(1000)
                        .setDataObj(userService.createUser(request)));
    }

    @GetMapping("/user/all")
    public ResponseEntity<ResponseModel<AllUserResponse>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseModel<AllUserResponse>(1000)
                        .setDataObj(userService.getAllUser()));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseModel<User>> getUserById(
            @PathVariable String id
    ){
      return ResponseEntity.status(HttpStatus.OK)
              .body(new ResponseModel<User>(1000)
                      .setDataObj(userService.getUserById(id)));
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<ResponseModel<User>> updateUser(
            @PathVariable String id,
            @RequestBody User request
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseModel<User>(1000)
                        .setDataObj(userService.updateUserById(id, request)));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ResponseModel<AllUserResponse>> deleteUserById(
            @PathVariable String id
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseModel<AllUserResponse>(1000)
                        .setDataObj(userService.deleteUserById(id)));
    }


}
