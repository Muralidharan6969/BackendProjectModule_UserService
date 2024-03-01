package com.example.backendprojectmodule_userservice.Repositories;

import com.example.backendprojectmodule_userservice.Models.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface NameRepository extends JpaRepository<Name, Long> {
    Optional<Name> findByFirstNameAndLastName(String firstName, String lastName);
    Name save(Name n);
}
