package com.seniorproject.project.service;

import com.seniorproject.project.exception.ProjectException;
import com.seniorproject.project.model.PaperSize;
import com.seniorproject.project.model.PaperSizeResponse;
import com.seniorproject.project.repository.PaperSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperSizeService {

    @Autowired
    private PaperSizeRepository paperSizeRepository;

    public PaperSizeResponse getAllPaperSize(){
        PaperSizeResponse response = new PaperSizeResponse();
        List<PaperSize> paperSizes = paperSizeRepository.getAllPaperSize();

        if(paperSizes != null && paperSizes.size()> 0){
            return response.setPaperSizes(paperSizes);
        }
        else{
            throw new ProjectException(1001);
        }
    }
}
