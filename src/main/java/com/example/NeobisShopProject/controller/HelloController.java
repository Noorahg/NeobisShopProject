package com.example.NeobisShopProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

public class HelloController {
    @GetMapping("/j")
    public String index() {
        return "Greetings from Spring Bootghghgh!";
    }


}
