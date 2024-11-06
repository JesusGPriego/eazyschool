package com.suleware.eazyschool.example_18.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;  

@Controller
@RequestMapping()
public class HomeController {
    

    
    @GetMapping(value = {"", "/", "/home"})
    public String getHome(Model model) {
        model.addAttribute("username", "Jane Doe");
        return "home.html";
    }
}
