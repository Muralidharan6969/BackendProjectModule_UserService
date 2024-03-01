package com.example.backendprojectmodule_userservice.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class GeoLocation extends BaseModel{
    private String lat;
    private String lng;
}
