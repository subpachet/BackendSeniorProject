package com.seniorproject.project.controller;

import com.seniorproject.project.model.ColorTypeResponseModel;
import com.seniorproject.project.model.responsemodel.ResponseModel;
import com.seniorproject.project.service.ColorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrintingController {

    @Autowired
    private ColorTypeService colorTypeService;

    @GetMapping("/printing-type")
    public ResponseEntity<ResponseModel<ColorTypeResponseModel>> getAllColorType(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseModel<ColorTypeResponseModel>(1000).setDataObj(colorTypeService.getAllColorType()));
    }

}
