package com.folklabor.customerreport.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
    @GetMapping("/")
    public String forwardToDocs(){
        return "redirect:/swagger-ui.html";
    }
}
