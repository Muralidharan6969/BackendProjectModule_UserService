package com.example.backendprojectmodule_userservice.DTOs;

import com.example.backendprojectmodule_userservice.Models.Address;
import com.example.backendprojectmodule_userservice.Models.Name;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreToUserDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private Name name;
    private Address address;
    private String phone;
}
