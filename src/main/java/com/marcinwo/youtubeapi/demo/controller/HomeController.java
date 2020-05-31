package com.marcinwo.youtubeapi.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Feels like home");
    }


}
