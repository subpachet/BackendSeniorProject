package com.seniorproject.project.service;

import com.seniorproject.project.exception.ProjectException;
import com.seniorproject.project.model.AllFileResponse;
import com.seniorproject.project.model.FileStorage;
import com.seniorproject.project.model.User;
import com.seniorproject.project.model.UserFileResponse;
import com.seniorproject.project.model.responsemodel.ResponseModel;
import com.seniorproject.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserFileService {
    @Autowired
    private UserService userService;
    @Autowired
    private FileStorageService fileStorageService;

    public ResponseModel<UserFileResponse> findFilesByUserId(String userId){
        User user = userService.getUserById(userId);
        AllFileResponse allFileResponse = fileStorageService.getAllFiles();
        List<FileStorage> fileStorageList = allFileResponse.getFileStorages().stream()
                                            .filter(fileStorage -> fileStorage.getUserID().equals(userId))
                                            .collect(Collectors.toList());

        ResponseModel<UserFileResponse> response = new ResponseModel<>(1000);
        UserFileResponse dataresponse = new UserFileResponse().setUserName(user.getUserName())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setFileStorages(fileStorageList);
        if(fileStorageList == null || fileStorageList.size()<1){
             response.setCode(3003);
             return response.setDataObj(dataresponse);
        }
        return response.setDataObj(dataresponse);
    }
}
