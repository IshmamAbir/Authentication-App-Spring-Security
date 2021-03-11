package com.example.security.model;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "authority")
@Setter
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;
    @Column
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public long getRoleId() {
        return roleId;
    }
}
