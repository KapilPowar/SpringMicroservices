package com.kapil.springbootrestapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/hello-user")
    public String welcomeString(){
        return " Hello user";
    }

}
