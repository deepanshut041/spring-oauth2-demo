package io.github.deepanshut041.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value="/")
    public String home(){
        return "Home";
    }

    @GetMapping(value="/private")
    public String privateArea(){
        return "Private Home";
    }
}
