package com.seniorproject.project.controller.advice;

import com.seniorproject.project.exception.ProjectException;
import com.seniorproject.project.model.responsemodel.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ResponseModel<Void>> handleException(ProjectException ex){
        log.info(ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseModel<>(1999));
    }

    @ExceptionHandler(ProjectException.class)
    @ResponseBody
    public ResponseEntity<ResponseModel<Void>> handleMyException(ProjectException ex){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseModel<>(ex.getCode()));
    }

}
