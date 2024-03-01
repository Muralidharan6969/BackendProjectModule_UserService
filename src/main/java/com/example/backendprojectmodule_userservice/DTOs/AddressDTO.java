package com.example.backendprojectmodule_userservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private String street;
    private String city;
    private int number;
    private String zipcode;
    private GeoLocationDTO geoLocation;
}
