package com.example.backendprojectmodule_userservice.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Name extends BaseModel{
    private String firstName;
    private String lastName;
}
