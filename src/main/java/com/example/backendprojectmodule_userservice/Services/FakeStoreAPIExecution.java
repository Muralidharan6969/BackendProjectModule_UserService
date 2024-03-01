package com.example.backendprojectmodule_userservice.Services;

import com.example.backendprojectmodule_userservice.DTOs.FakeStoreToUserDTO;
import com.example.backendprojectmodule_userservice.Models.Token;
import com.example.backendprojectmodule_userservice.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("fakeStoreAPIExecution")
public class FakeStoreAPIExecution implements UserServiceInterface{
    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreAPIExecution(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private User mapFakeStoreToUser(FakeStoreToUserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<FakeStoreToUserDTO> userDTOs = Arrays.asList(restTemplate.getForObject
                ("https://fakestoreapi.com/users", FakeStoreToUserDTO[].class));
        return userDTOs.stream().map(userDTO -> mapFakeStoreToUser(userDTO))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserById(Long id) {
        return mapFakeStoreToUser(restTemplate.getForObject
                ("https://fakestoreapi.com/users/" + id, FakeStoreToUserDTO.class));
    }

    @Override
    public User signUp(User user) {
        FakeStoreToUserDTO userDTO = restTemplate.postForObject("https://fakestoreapi.com/users",
                new FakeStoreToUserDTO(), FakeStoreToUserDTO.class);
        return mapFakeStoreToUser(userDTO);
    }

    @Override
    public User updateUser(Long id, User user) {
        FakeStoreToUserDTO userDTO = restTemplate.patchForObject
                ("https://fakestoreapi.com/users/" + id,
                new FakeStoreToUserDTO(), FakeStoreToUserDTO.class);
        return mapFakeStoreToUser(userDTO);
    }

    @Override
    public void deleteUser(Long id) {
        restTemplate.delete("https://fakestoreapi.com/users/" + id);
    }

    @Override
    public Token userLogin(String username, String password) {
        return null;
    }

    @Override
    public void userLogout(String token) {

    }

    @Override
    public User validateToken(String token) {
        return null;
    }
}
