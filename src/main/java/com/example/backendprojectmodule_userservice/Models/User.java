package com.example.backendprojectmodule_userservice.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String email;
    private String username;
    private String password;
    @OneToOne (cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Name name;
    @OneToOne (cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Address address;
    private String phone;
    @ManyToMany (fetch = FetchType.EAGER)
    private List<Role> roles;
}
