package com.user.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/v1/users/demo")
public class DemoController {
    @GetMapping
    public ResponseEntity<?> getDemo(){
        return ResponseEntity.ok("Hello World, SECURED ENDPOINT");
    }
}
