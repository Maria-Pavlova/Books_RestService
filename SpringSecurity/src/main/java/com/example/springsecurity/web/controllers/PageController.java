package com.example.springsecurity.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/pages/all")
    public String all() {
        return "all";
    }

    @GetMapping("/pages/moderator")
    public String moderator() {
        return "moderator";
    }

    @GetMapping("/pages/admin")
    public String admin() {
        return "admin";
    }
}
