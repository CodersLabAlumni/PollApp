package com.pollapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RedirectController {

    @GetMapping("/")
    String home() {
        return "pollapp";
    }

    @GetMapping("/login")
    String login() {
        return "pollapp_logged";
    }
}
