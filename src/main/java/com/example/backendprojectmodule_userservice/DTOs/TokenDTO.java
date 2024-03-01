package com.example.backendprojectmodule_userservice.DTOs;

import com.example.backendprojectmodule_userservice.Models.Token;
import com.example.backendprojectmodule_userservice.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TokenDTO {
    private String value;
    private User user;
    private Date expiryAt;

    public static TokenDTO fromEntity(Token token) {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setValue(token.getValue());
        tokenDTO.setUser(token.getUser());
        tokenDTO.setExpiryAt(token.getExpiryAt());
        return tokenDTO;
    }
}
