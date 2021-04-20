package com.seniorproject.project.controller;

import com.seniorproject.project.model.ColorTypeResponseModel;
import com.seniorproject.project.model.PaperSizeResponse;
import com.seniorproject.project.model.responsemodel.ResponseModel;
import com.seniorproject.project.service.ColorTypeService;
import com.seniorproject.project.service.PaperSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaperSizeController {
    @Autowired
    private PaperSizeService paperSizeService;

    @GetMapping("/paper-type")
    public ResponseEntity<ResponseModel<PaperSizeResponse>> getAllPaperType(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseModel<PaperSizeResponse>(1000).setDataObj(paperSizeService.getAllPaperSize()));
    }


}
