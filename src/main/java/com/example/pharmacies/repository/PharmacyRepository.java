package com.example.pharmacies.repository;

import com.example.pharmacies.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PharmacyRepository extends JpaRepository<Pharmacy,Long> {
    @Override
    List<Pharmacy> findAll();
    List<Pharmacy> findAllByCity(String location);
    List<Pharmacy> findAllByCityContains(String city);

}
