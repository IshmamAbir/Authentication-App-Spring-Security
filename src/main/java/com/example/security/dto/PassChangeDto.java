package com.example.security.dto;

import lombok.Data;

@Data
public class PassChangeDto {
    private long userId;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public PassChangeDto(long userId) {
        this.userId = userId;
    }
}
