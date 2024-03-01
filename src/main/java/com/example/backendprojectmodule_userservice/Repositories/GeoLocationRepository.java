package com.example.backendprojectmodule_userservice.Repositories;

import com.example.backendprojectmodule_userservice.Models.GeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeoLocationRepository extends JpaRepository<GeoLocation, Long> {
    Optional<GeoLocation> findByLatAndLng(String lat, String lng);
    GeoLocation save(GeoLocation g);
}
