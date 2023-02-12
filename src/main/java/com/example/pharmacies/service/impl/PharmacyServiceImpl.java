package com.example.pharmacies.service.impl;

import com.example.pharmacies.model.Pharmacy;
import com.example.pharmacies.model.exception.CityNotFoundException;
import com.example.pharmacies.model.exception.PharmacyAlreadyExistsException;
import com.example.pharmacies.repository.PharmacyRepository;
import com.example.pharmacies.service.PharmacyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Service
public class PharmacyServiceImpl implements PharmacyService {
    private final PharmacyRepository pharmacyRepository;

    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public List<Pharmacy> listAllPharmacies() {
        return pharmacyRepository.findAll();
    }

    @Override
    public List<Pharmacy> listAllPharmaciesByCity(String city) {
        if (pharmacyRepository.findAllByCity(city) == null) {
            try {
               throw new CityNotFoundException("City could not be found!");
            } catch (CityNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else
            return pharmacyRepository.findAllByCity(city);
    }

    @Override
    public List<Pharmacy> findByContains(String city) {
        return pharmacyRepository.findAllByCityContains(city);
    }

    @Override
    public void savePharmacy(Long id, String name, String location, String workingTime, String lon, String lat, String phoneNum, String city, String website) {
        if(this.pharmacyRepository.findById(id).isPresent())
            try {
                throw new PharmacyAlreadyExistsException(String.format("Pharmacy with id %f already exists!",id));
            } catch (PharmacyAlreadyExistsException e) {
                e.printStackTrace();
            }
        this.pharmacyRepository.findAll().add(new Pharmacy(name,location,workingTime,Double.valueOf(lon),
                Double.valueOf(lat),phoneNum,city,website));
    }

    @Override
    public void savePharmacy(Pharmacy pharmacy) {
        pharmacyRepository.save(pharmacy);
    }

    @Override
    public Pharmacy findClosestTo(Pharmacy p) {
        List<Pharmacy> all=this.listAllPharmacies();
        Double distanceBetweenPharmacies=Double.MAX_VALUE;
        Long idOfClosest=Long.valueOf(0);
        for(Pharmacy pharmacy:all)
        {
            Double helper=distance(pharmacy.getLat(),pharmacy.getLon(),p.getLat(),p.getLon());
            if(helper==Double.valueOf(0))
                continue;
            if(helper<distanceBetweenPharmacies)
            {
                distanceBetweenPharmacies=helper;
                idOfClosest=pharmacy.getId();
            }
        }
          Pharmacy closest=pharmacyRepository.getById(idOfClosest);
        return closest;
    }

    private Double distance(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return Double.valueOf(0);
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;
            return (Double.valueOf(dist));
        }
    }

}
