package com.seniorproject.project.controller.advice;

import com.seniorproject.project.exception.ProjectException;
import com.seniorproject.project.model.responsemodel.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ProjectException.class)
    @ResponseBody
    public ResponseEntity<ResponseModel<Void>> handleMyException(ProjectException ex){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseModel<>(ex.getCode()));
    }

}
