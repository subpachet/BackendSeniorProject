package com.seniorproject.project.service;

import com.seniorproject.project.exception.ProjectException;
import com.seniorproject.project.model.AllFileResponse;
import com.seniorproject.project.model.ColorType;
import com.seniorproject.project.model.ColorTypeResponseModel;
import com.seniorproject.project.model.FileStorage;
import com.seniorproject.project.model.responsemodel.ResponseModel;
import com.seniorproject.project.repository.ColorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorTypeService {
    @Autowired
    private ColorTypeRepository colorTypeRepository;

    public ColorTypeResponseModel getAllColorType(){
        ColorTypeResponseModel response = new ColorTypeResponseModel();
        List<ColorType> colorTypes = colorTypeRepository.getAllColorType();

        if(colorTypes != null && colorTypes.size()> 0){
            return response.setColorTypes(colorTypes);
        }
        else{
            throw new ProjectException(1001);
        }
    }
}
