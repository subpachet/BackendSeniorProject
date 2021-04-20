package com.seniorproject.project.controller;

import com.seniorproject.project.model.*;
import com.seniorproject.project.model.responsemodel.ResponseModel;
import com.seniorproject.project.service.FileStorageService;
import com.seniorproject.project.service.UserPrintingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserPrintingController {
    @Autowired
    private UserPrintingService userPrintingService;

    @PostMapping("/userprinting/create")
    public ResponseEntity<ResponseModel> createUserPrinting(
            @RequestBody UserPrintingRequest request
    ){
        userPrintingService.createUserPrintingData(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseModel(1000));

    }

    @GetMapping("/queue")
    public ResponseEntity<ResponseModel<QueueRemainingResponse>> getRemainingQueue(){

        return ResponseEntity.status(HttpStatus.OK)
                .body(userPrintingService.getRemainingQueue());
    }

    @GetMapping("/checkorder/{userId}")
    public ResponseEntity<ResponseModel<QueueRemainingResponse>> getCheckOrder(
            @PathVariable String userId
    ){

        return ResponseEntity.status(HttpStatus.OK)
                .body(userPrintingService.getCheckOrder(userId));
    }

    @GetMapping("/unpickupprinting")
    public ResponseEntity<ResponseModel<QueueRemainingResponse>> getAllNotPickup(){

        return ResponseEntity.status(HttpStatus.OK)
                .body(userPrintingService.getAllUnpickedUp());
    }

    @GetMapping("/userprintingstatus/{statusid}")
    public ResponseEntity<ResponseModel<UserPrintingStatus>> getUserPrintStatus(
            @PathVariable String statusid
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userPrintingService.getUserPrintingStatusById(statusid));
    }

    @PatchMapping("/edit/coststatus/{statusid}")
    public ResponseEntity<ResponseModel> patchEditCostStatus(
            @PathVariable String statusid,
            @RequestBody PatchCostStatusRequest request
            ){
        userPrintingService.patchEditCostStatus(statusid,request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseModel(1000));
    }
}
