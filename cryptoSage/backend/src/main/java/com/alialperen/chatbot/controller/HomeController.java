package com.alialperen.chatbot.controller;

import com.alialperen.chatbot.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    private ResponseEntity<ApiResponse> helloHome(){
        ApiResponse res = new ApiResponse();
        res.setMessage("Hello CryptoSage Page");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
