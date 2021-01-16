package com.seniorproject.project.service;

import com.seniorproject.project.model.Addnumber;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public int addNumber(int a,int b){
        return a + b;
    }

    public int addNumberBody(Addnumber request){
        return request.getA() + request.getB();
    }
}
