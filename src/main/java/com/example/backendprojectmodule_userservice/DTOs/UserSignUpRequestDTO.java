package com.example.backendprojectmodule_userservice.DTOs;

import com.example.backendprojectmodule_userservice.Models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequestDTO {
    private String username;
    private String email;
    private String password;
    private String phone;
    private NameDTO name;
    private AddressDTO address;

    public static UserSignUpRequestDTO convertToDTO(User user) {
        UserSignUpRequestDTO userSignUpRequestDTO = new UserSignUpRequestDTO();
        userSignUpRequestDTO.setUsername(user.getUsername());
        userSignUpRequestDTO.setEmail(user.getEmail());
        userSignUpRequestDTO.setPassword(user.getPassword());
        userSignUpRequestDTO.setPhone(user.getPhone());
        userSignUpRequestDTO.setName(new NameDTO());
        userSignUpRequestDTO.getName().setFirstName(user.getName().getFirstName());
        userSignUpRequestDTO.getName().setLastName(user.getName().getLastName());
        userSignUpRequestDTO.setAddress(new AddressDTO());
        userSignUpRequestDTO.getAddress().setStreet(user.getAddress().getStreet());
        userSignUpRequestDTO.getAddress().setCity(user.getAddress().getCity());
        userSignUpRequestDTO.getAddress().setNumber(user.getAddress().getNumber());
        userSignUpRequestDTO.getAddress().setZipcode(user.getAddress().getZipcode());
        userSignUpRequestDTO.getAddress().setGeoLocation(new GeoLocationDTO());
        userSignUpRequestDTO.getAddress().getGeoLocation().setLat(user.getAddress().getGeoLocation().getLat());
        userSignUpRequestDTO.getAddress().getGeoLocation().setLng(user.getAddress().getGeoLocation().getLng());
        return userSignUpRequestDTO;
    }
}
