package com.example.security.controller;

import com.example.security.dto.PassChangeDto;
import com.example.security.dto.UserDto;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import com.example.security.service.UserService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    String show(){
        return "user/user";
    }

    @GetMapping("/changePassword")
    public String changePassword(Model model){
        User currentUser= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId=currentUser.getId();
        model.addAttribute("reset",new PassChangeDto(userId));
        model.addAttribute("message","");
        return "user/changePassword";
    }
    @PostMapping("/applyChange")
    public String applyNewPassword(@ModelAttribute PassChangeDto passChangeDto,Model model){
        UserDto userDto= userService.getUserById(passChangeDto.getUserId());
        boolean matched=userService.matchPassword(passChangeDto.getOldPassword(),userDto.getPassword());
        if (matched==true && passChangeDto.getNewPassword().equals(passChangeDto.getConfirmPassword())){
            String message=userService.changePass(passChangeDto.getUserId(),passChangeDto.getNewPassword());
            model.addAttribute("message",message);
        }else {
            model.addAttribute("message","Password not matched");
        }
        return "user/changePassword";
    }

}
