package com.example.backendprojectmodule_userservice.Services;

import com.example.backendprojectmodule_userservice.DTOs.UserSignUpRequestDTO;
import com.example.backendprojectmodule_userservice.Models.*;

import java.util.List;

public interface UserServiceInterface {
    public default User convertDtoToUser(UserSignUpRequestDTO userSignUpRequestDTO){
        User u = new User();
        u.setUsername(userSignUpRequestDTO.getUsername());
        u.setEmail(userSignUpRequestDTO.getEmail());
        u.setPassword(userSignUpRequestDTO.getPassword());
        u.setName(new Name());
        u.getName().setFirstName(userSignUpRequestDTO.getName().getFirstName());
        u.getName().setLastName(userSignUpRequestDTO.getName().getLastName());
        u.setAddress(new Address());
        u.getAddress().setStreet(userSignUpRequestDTO.getAddress().getStreet());
        u.getAddress().setCity(userSignUpRequestDTO.getAddress().getCity());
        u.getAddress().setNumber(userSignUpRequestDTO.getAddress().getNumber());
        u.getAddress().setZipcode(userSignUpRequestDTO.getAddress().getZipcode());
        u.getAddress().setGeoLocation(new GeoLocation());
        u.getAddress().getGeoLocation().setLat(userSignUpRequestDTO.getAddress().getGeoLocation().getLat());
        u.getAddress().getGeoLocation().setLng(userSignUpRequestDTO.getAddress().getGeoLocation().getLng());
        u.setPhone(userSignUpRequestDTO.getPhone());
        return u;
    }
    public List<User> getAllUsers();
    public User getUserById(Long id);
    public User signUp(User user);
    public User updateUser(Long id, User user);
    public void deleteUser(Long id);
    public Token userLogin(String username, String password);
    public void userLogout(String token);

    public User validateToken(String token);
}
