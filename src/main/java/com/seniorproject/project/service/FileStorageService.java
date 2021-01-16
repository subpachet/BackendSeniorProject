package com.seniorproject.project.service;

import com.seniorproject.project.exception.ProjectException;
import com.seniorproject.project.model.AllFileResponse;
import com.seniorproject.project.model.FileStorage;
import com.seniorproject.project.repository.FileStorageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FileStorageService {

    @Autowired
    private FileStorageRepository fileStorageRepository;

    public void createFile(FileStorage fileStorage){
        int isSuccess =fileStorageRepository.insertFiles(fileStorage) ;
        if(isSuccess == 0){
            throw new ProjectException(3001);
        }
    }
    public AllFileResponse getAllFiles(){
        AllFileResponse response = new AllFileResponse();
        List<FileStorage> fileStorages = fileStorageRepository.getAllFiles();

        if(fileStorages != null && fileStorages.size()> 0){
            return response.setFileStorages(fileStorages);
        }
        else{
            throw new ProjectException(3000);
        }
    }

    public FileStorage getFileById(int id){
        List<FileStorage> fileStorages = fileStorageRepository.getFileById(id);
        if(fileStorages != null && fileStorages.size() > 0){
            return fileStorages.get(0);
        }
        else{
            throw new ProjectException(3000);
        }
    }

    public AllFileResponse deleteFileById(int id){
        AllFileResponse response = new AllFileResponse();
        List<FileStorage> fileStorages = fileStorageRepository.deleteFileById(id);

        if(fileStorages != null && fileStorages.size()> 0){
            return response.setFileStorages(fileStorages);
        }
        else{
            throw new ProjectException(3002);
        }
    }
}
