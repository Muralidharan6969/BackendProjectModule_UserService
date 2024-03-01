package com.example.backendprojectmodule_userservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDTO {
    private String username;
    private String password;
}
