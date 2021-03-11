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
        Optional<Authority> optionalAuthority=authorityRepository.findById(1L);
        Authority authority= optionalAuthority.get();
        userList.add(authority);
        user.setAuthorities(userList);
        userRepository.save(user);
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}