package com.example.backendprojectmodule_userservice.Security.Services;

import com.example.backendprojectmodule_userservice.Models.User;
import com.example.backendprojectmodule_userservice.Repositories.UserRepository;

import com.example.backendprojectmodule_userservice.Security.Models.JPAUserDetails;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JPAUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public JPAUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        JPAUserDetails userDetails = new JPAUserDetails(user.get());
        return userDetails;
    }
}
