package com.example.backendprojectmodule_userservice.Controllers;

import com.example.backendprojectmodule_userservice.DTOs.*;
import com.example.backendprojectmodule_userservice.Models.User;
import com.example.backendprojectmodule_userservice.Services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserServiceInterface userService;

    @Autowired
    public UserController(@Qualifier("selfAPIExecution") UserServiceInterface userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/signup")
    public UserSignUpRequestDTO signUp(@RequestBody UserSignUpRequestDTO user){
        return UserSignUpRequestDTO.convertToDTO(userService.signUp(userService.convertDtoToUser(user)));
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public TokenDTO userLogin(@RequestBody UserLoginRequestDTO userLoginRequestDTO){
        return TokenDTO.fromEntity(
                userService.userLogin(userLoginRequestDTO.getUsername(),
                        userLoginRequestDTO.getPassword()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> userLogout(@RequestBody UserLogoutRequestDTO userLogoutRequestDTO){
        userService.userLogout(userLogoutRequestDTO.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public User validateToken(@PathVariable("token") String token){
        return userService.validateToken(token);
    }
}
