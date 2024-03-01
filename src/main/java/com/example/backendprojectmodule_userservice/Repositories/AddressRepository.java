package com.example.backendprojectmodule_userservice.Repositories;

import com.example.backendprojectmodule_userservice.Models.Address;
import com.example.backendprojectmodule_userservice.Models.GeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a WHERE a.street = :street AND " +
            "a.city = :city AND a.number = :number AND a.zipcode = :zipcode")
    Optional<Address> findByCustomDetails1(String street, String city, int number, String zipcode);
    @Query("SELECT a FROM Address a WHERE a.geoLocation = :geoLocation")
    Optional<Address> findByCustomDetails2(GeoLocation geoLocation);
    Address save(Address a);
}
