package com.example.security.service;

import com.example.security.dto.UserDto;
import com.example.security.model.Authority;
import com.example.security.model.User;
import com.example.security.repository.AuthorityRepository;
import com.example.security.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        return user;
    }

    public void saveUser(UserDto userDto){
        User user=new User();
        BeanUtils.copyProperties(userDto,user);
        user.setPassword(passwordEncoder().encode(userDto.getPassword()));
        List<Authority> userList=new ArrayList<>();
        Optional<Authority> optionalAuthority=authorityRepository.findById(1L); //Change Role here Manually by setting findById 1L or 2L
        Authority authority= optionalAuthority.get();
        userList.add(authority);
        user.setAuthorities(userList);
        userRepository.save(user);
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public List<UserDto> getAllUser(){
//        List<User> userList=userRepository.findAllUsers();
        List<User> userList=userRepository.findAllByAuthorities_Authority("User");
        List<UserDto> userDtoList=new ArrayList<>();

        for (User user:userList) {
            UserDto userDto=new UserDto();
            BeanUtils.copyProperties(user,userDto);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public UserDto getUserById(long id){
        Optional<User> optional=userRepository.findById(id);
        User user=optional.get();
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user,userDto);
        userDto.setAuthorities((List<Authority>) user.getAuthorities());
        return userDto;
    }

    public boolean matchPassword(String oldPassword, String password) {
        boolean result;

        if (passwordEncoder().matches(oldPassword,password)){
            result=true;
        }else {
            result = false;
        }
        return result;

    }


    public String changePass(long userId, String newPassword) {
        User user= userRepository.findById(userId).get();
        if (user!=null){
            user.setPassword(passwordEncoder().encode(newPassword));
            userRepository.save(user);
            return "Save Successful";
        }else
            return "Save failed";

    }
}
