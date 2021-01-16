package com.seniorproject.project.service;

import com.seniorproject.project.exception.ProjectException;
import com.seniorproject.project.model.AllUserResponse;
import com.seniorproject.project.model.User;
import com.seniorproject.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        int isSuccess =userRepository.insertUser(user) ;
        if(isSuccess == 1){
            return user;
        }
        else{
            throw new ProjectException(2001);
        }
    }

    public AllUserResponse getAllUser(){
        AllUserResponse response = new AllUserResponse();
        List<User> users = userRepository.getAllUser();

        if(users != null && users.size()> 0){
            return response.setUsers(users);
        }
        else{
            throw new ProjectException(1001);
        }
    }

    public User getUserById(String id){
        List<User> users =userRepository.getUserById(id);
        if(users != null && users.size() > 0){
            return users.get(0);
        }
        else{
            throw new ProjectException(1001);
        }
    }

    public User updateUserById(String id,User user){
        return userRepository.updateUserById(id,user);
    }

    public AllUserResponse deleteUserById(String id){
        AllUserResponse response = new AllUserResponse();
        List<User> users = userRepository.deleteUserById(id);

        if(users != null && users.size()> 0){
            return response.setUsers(users);
        }
        else{
            throw new ProjectException(1001);
        }
    }
}
