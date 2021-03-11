package com.example.security.controller;

import com.example.security.dto.UserDto;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String homepage(){
        return "/homepage";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model){
        model.addAttribute("userDto",new UserDto());
        return "signup";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute UserDto userDto){
        userService.saveUser(userDto);

        return "redirect:/login";
    }
    @RequestMapping("/denied")
    public String denied(){
        return "denied";
    }
}
