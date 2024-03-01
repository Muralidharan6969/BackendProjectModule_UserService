package com.example.backendprojectmodule_userservice.Repositories;

import com.example.backendprojectmodule_userservice.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);

    Optional<Token> findByValueAndDeleted(String value, boolean deleted);
    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(
            String value, boolean deleted, Date expiryAt);
}
