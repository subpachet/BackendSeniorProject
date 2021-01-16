package com.seniorproject.project.controller;

import com.seniorproject.project.model.Addnumber;
import com.seniorproject.project.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String Hello(){
        return "Hello";
    }

    @PostMapping("/addnumber")
    public int addNumber(
            @RequestParam int a,
            @RequestParam int b
    ){
        int response = helloService.addNumber(a,b);
        return response;
    }

    @GetMapping("/addnumber")
    public int add(
            @RequestBody Addnumber request
    ){
        int response = helloService.addNumberBody(request);
        return response;
    }

    @GetMapping("/{a}/add/{b}")
    public int addNumberbyPath(
            @PathVariable int a,
            @PathVariable int b
    ){
        int response = helloService.addNumber(a, b);
        return response;
    }
}
