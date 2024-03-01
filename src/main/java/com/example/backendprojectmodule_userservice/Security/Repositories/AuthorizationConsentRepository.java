package com.example.backendprojectmodule_userservice.Security.Repositories;

import java.util.Optional;

//import sample.jpa.entity.authorizationconsent.AuthorizationConsent;

import com.example.backendprojectmodule_userservice.Security.Models.AuthorizationConsent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationConsentRepository extends JpaRepository<AuthorizationConsent, AuthorizationConsent.AuthorizationConsentId> {
    Optional<AuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);
    void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);
}
