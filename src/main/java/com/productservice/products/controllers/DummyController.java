package com.productservice.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DummyController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/dummy")
    public void dummyApi(){
        String object = restTemplate.getForObject("http://UserService/hi", String.class);
        System.out.println(object);
    }

}

