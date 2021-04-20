package com.seniorproject.project.service;

import com.seniorproject.project.exception.ProjectException;
import com.seniorproject.project.model.*;
import com.seniorproject.project.model.responsemodel.ResponseModel;
import com.seniorproject.project.repository.UserPrintingStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.seniorproject.project.constant.GlobalConstant.*;

@Slf4j
@Service
public class UserPrintingService {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserPrintingStatusRepository userPrintingStatusRepository;

    public void createUserPrintingData(UserPrintingRequest request){

        try{
            fileStorageService.createFile(request.getFileStorage());
        }catch (Exception ex){
            throw new ProjectException(1899);
        }
        UserPrintingStatus userPrintingStatus = request.getUserPrintingStatus();

        FileStorage fileStorage = fileStorageService.getFileByUserIdFileName(request.getFileStorage().getUserID(),request.getFileStorage().getFileName()).get(0);
        userPrintingStatus.setUserId(fileStorage.getUserID())
                .setFileId(fileStorage.getId())
                .setFileName(fileStorage.getFileName())
                .setFilePath(fileStorage.getPath());

        int status = 0;
        try{
            status = userPrintingStatusRepository.insertPrintingStatus(userPrintingStatus);
        }catch (Exception ex){
            throw new ProjectException(1899);
        }
        if(status == 0){
            throw new ProjectException(1899);
        }

    }

    public ResponseModel<QueueRemainingResponse> getRemainingQueue(){
        List<UserPrintingStatus> queue = userPrintingStatusRepository.getAllPrintingStatus()
                .stream()
                .filter(userPrintingStatus -> userPrintingStatus.getStatus().equals(STATUS_IN_QUEUE)
                        || userPrintingStatus.getStatus().equals(STATUS_IN_PROGRESS))
                .collect(Collectors.toList());


        Collections.sort(queue, Collections.reverseOrder());
        QueueRemainingResponse queueRemaining = new QueueRemainingResponse();
        queueRemaining.setTotalRemaining(queue.size());
        queueRemaining.setPrintingList(queue);
        ResponseModel<QueueRemainingResponse> response=
                new ResponseModel<QueueRemainingResponse>(1000)
                .setDataObj(queueRemaining);

        return response;
    }

    public ResponseModel<QueueRemainingResponse> getCheckOrder(String userId){
        List<UserPrintingStatus> queue = userPrintingStatusRepository.getAllPrintingStatus()
                .stream()
                .filter(userPrintingStatus -> userPrintingStatus.getUserId().equals(userId) && !userPrintingStatus.getStatus().equals(STATUS_PICKED_UP))
                .collect(Collectors.toList());

        Collections.sort(queue, Collections.reverseOrder());
        QueueRemainingResponse queueRemaining = new QueueRemainingResponse();
        queueRemaining.setTotalRemaining(queue.size());
        queueRemaining.setPrintingList(queue);
        ResponseModel<QueueRemainingResponse> response=
                new ResponseModel<QueueRemainingResponse>(1000)
                        .setDataObj(queueRemaining);

        return response;
    }

    public ResponseModel<QueueRemainingResponse> getAllUnpickedUp(){
        List<UserPrintingStatus> queue = userPrintingStatusRepository.getAllPrintingStatus()
                .stream()
                .filter(userPrintingStatus ->  !userPrintingStatus.getStatus().equals(STATUS_PICKED_UP))
                .collect(Collectors.toList());

        QueueRemainingResponse queueRemaining = new QueueRemainingResponse();
        queueRemaining.setTotalRemaining(queue.size());
        queueRemaining.setPrintingList(queue);
        ResponseModel<QueueRemainingResponse> response=
                new ResponseModel<QueueRemainingResponse>(1000)
                        .setDataObj(queueRemaining);

        return response;
    }

    public ResponseModel<UserPrintingStatus> getUserPrintingStatusById(String statusid){

        UserPrintingStatus status = userPrintingStatusRepository.getPrintingStatusById(Integer.parseInt(statusid)).get(0);

        ResponseModel<UserPrintingStatus> response = new ResponseModel<UserPrintingStatus>(1000);
        response.setDataObj(status);

        return response;
    }

    public void patchEditCostStatus(String userId, PatchCostStatusRequest request){
        int status = 0;
        try{
            status = userPrintingStatusRepository.patchUpdateCostStatus(Integer.parseInt(userId),request);
        }catch (Exception ex){
            throw new ProjectException(1899);
        }

        if(status == 0){
            throw new ProjectException(3004);
        }
    }
}
