package com.example.wisestudies.controller.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tryit")
@RequiredArgsConstructor
public class tryController {
    @GetMapping("/try")
    public ResponseEntity<String> tryIt(){
        return ResponseEntity.ok("Hello I'm just trying");
    }

}
