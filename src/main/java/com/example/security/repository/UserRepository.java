package com.example.security.repository;

import com.example.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    /*@Query(value = "SELECT u from user u where u.authorities <> 'User'")
    List<User> findAllUsers();*/

    @Query
    List<User> findAllByAuthorities_Authority(String name);
}
