package com.example.backendprojectmodule_userservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.Date;

@Getter
@Setter
@Entity
public class Token extends BaseModel{
    private String value;
    @ManyToOne
    private User user;
    private Date expiryAt;
}
