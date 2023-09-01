package com.jwt.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/index")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String homeHandler(){
        return "Hii welcome to the unauthorized statement";
    }


    @GetMapping("/auth")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String anotherhandler(){
        return "Hii this is user access statement only!!";
    }

}
