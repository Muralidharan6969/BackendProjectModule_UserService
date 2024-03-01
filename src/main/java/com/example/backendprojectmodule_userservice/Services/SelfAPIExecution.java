package com.example.backendprojectmodule_userservice.Services;

import com.example.backendprojectmodule_userservice.DTOs.UserSignUpSendEmailDTO;
import com.example.backendprojectmodule_userservice.Models.*;
import com.example.backendprojectmodule_userservice.Repositories.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.random.RandomGenerator;

@Service("selfAPIExecution")
public class SelfAPIExecution implements UserServiceInterface {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;
    private KafkaTemplate kafkaTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public SelfAPIExecution(UserRepository userRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder,
                            TokenRepository tokenRepository,
                            KafkaTemplate kafkaTemplate,
                            ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }
    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User signUp(User user) {
//        nameRepository.save(user.getName());
//        geoLocationRepository.save(user.getAddress().getGeoLocation());
//        addressRepository.save(user.getAddress());
//        Optional<Name> nameOptional = nameRepository.findByFirstNameAndLastName
//                (user.getName().getFirstName(), user.getName().getLastName());
//        if(nameOptional.isEmpty()){
//            nameRepository.save(user.getName());
//        }
//        else{
//            user.setName(nameOptional.get());
//        }
//        Address a = addAddress(user.getAddress());
//        user.setAddress(a);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User user1 = userRepository.save(user);
        UserSignUpSendEmailDTO sendEmailDTO = new UserSignUpSendEmailDTO();
        sendEmailDTO.setSendTo(user1.getEmail());
        sendEmailDTO.setSubject("Welcome to our platform");
        sendEmailDTO.setBody("Welcome to our platform. Your username is: " + user1.getUsername());
        try {
            kafkaTemplate.send("sendEmail", objectMapper.writeValueAsString(sendEmailDTO));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return user1;
    }

    private Address addAddress(Address address) {
//        Optional<GeoLocation> geoLocationOptional = geoLocationRepository.findByLatAndLng
//                (address.getGeoLocation().getLat(), address.getGeoLocation().getLng());
//        if(geoLocationOptional.isEmpty()){
//            geoLocationRepository.save(address.getGeoLocation());
//        }
//        else{
//            address.setGeoLocation(geoLocationOptional.get());
//        }
//        return addressRepository.save(address);
        return null;
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Token userLogin(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }
        if(!bCryptPasswordEncoder.matches(password, userOptional.get().getPassword())){
            throw new RuntimeException("Invalid password");
        }
        Token token = generateToken(userOptional.get());
        return tokenRepository.save(token);
    }

    private Token generateToken(User user) {
        Token token = new Token();
        token.setUser(user);
        LocalDate date = LocalDate.now();
        LocalDate expiryLocalDate = date.plusDays(30);
        Date expiryDate = Date.from(expiryLocalDate.atStartOfDay().atZone
                (java.time.ZoneId.systemDefault()).toInstant());
        token.setExpiryAt(expiryDate);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        return token;
    }

    @Override
    public void userLogout(String tokenValue) {
        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeleted(tokenValue, false);
        if(tokenOptional.isEmpty()){
            throw new RuntimeException("Invalid token");
        }
        Token token = tokenOptional.get();
        token.setDeleted(true);
        tokenRepository.save(token);
    }

    @Override
    public User validateToken(String token) {
        Optional<Token> tokenOptional = tokenRepository.
                findByValueAndDeletedAndExpiryAtGreaterThan(token, false, new Date());
        if(tokenOptional.isEmpty()){
            throw new RuntimeException("Invalid token");
        }
        return tokenOptional.get().getUser();
    }
}
