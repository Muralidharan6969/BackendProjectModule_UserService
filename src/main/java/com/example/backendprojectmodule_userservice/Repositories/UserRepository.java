package com.example.backendprojectmodule_userservice.Repositories;

import com.example.backendprojectmodule_userservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    @Override
    void deleteById(Long aLong);

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
