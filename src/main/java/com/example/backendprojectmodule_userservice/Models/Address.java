package com.example.backendprojectmodule_userservice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address extends BaseModel{
    private String street;
    private String city;
    private int number;
    private String zipcode;
    @OneToOne (cascade = CascadeType.PERSIST, orphanRemoval = true)
    private GeoLocation geoLocation;
}
