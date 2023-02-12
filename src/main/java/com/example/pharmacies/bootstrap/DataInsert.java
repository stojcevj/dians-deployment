package com.example.pharmacies.bootstrap;

import com.example.pharmacies.model.Pharmacy;
import com.example.pharmacies.repository.PharmacyRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Component
public class DataInsert {
    private final PharmacyRepository pharmacyRepository;

    public DataInsert(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @PostConstruct
    public void init() throws FileNotFoundException {
        /*BufferedReader br = new BufferedReader(new FileReader(""));

        br.lines().forEach(i->{
                   String [] line = i.split("\\|");
                   pharmacyRepository.save(new Pharmacy(line[2], line[3], line[7],
                           Double.parseDouble(line[0]), Double.parseDouble(line[1]), line[5], line[4], line[6]));
               });*/

   }

  }


