package com.example.security.init;

import com.example.security.model.Authority;
import com.example.security.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements CommandLineRunner {
    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {

//        authorityRepository.deleteAll();

        /*Authority authority1=new Authority();
        authority1.setAuthority("User");
        authorityRepository.save(authority1);

        Authority authority2=new Authority();
        authority2.setAuthority("Admin");
        authorityRepository.save(authority2);*/


    }
}
