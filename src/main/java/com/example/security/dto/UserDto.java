package com.example.security.dto;

import com.example.security.model.Authority;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
public class UserDto {
    private long id;
    private String username;
    private String password;

    private List<Authority> authorities;
    private List<Long> authorityIdList;
}
