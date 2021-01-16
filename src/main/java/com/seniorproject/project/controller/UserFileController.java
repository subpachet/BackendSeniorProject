package com.seniorproject.project.controller;

import com.seniorproject.project.model.FileStorage;
import com.seniorproject.project.model.UserFileResponse;
import com.seniorproject.project.model.responsemodel.ResponseModel;
import com.seniorproject.project.service.UserFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserFileController {
    @Autowired
    private UserFileService userFileService;

    @GetMapping("/user-file/{userid}")
    public ResponseEntity<ResponseModel<UserFileResponse>> getFileByUserId(
            @PathVariable String userid
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userFileService.findFilesByUserId(userid));
    }
}
