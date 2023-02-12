package com.example.pharmacies.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "apteka")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    //TODO:Change the name: location->address
    String location;

    String workingTime;

    Double lon;

    Double lat;

    String phoneNumber;

    String city;

    String website;

    public Pharmacy(String name, String location, String workingTime, Double lon, Double lat, String phoneNumber, String city, String website) {
        this.name = name;
        this.location = location;
        this.workingTime = workingTime;
        this.lon = lon;
        this.lat = lat;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.website = website;
    }
   public Double getLon()
   {
       return this.lon;
   }
  public Double getLat()
   {
       return this.lat;
   }

    public Pharmacy() {

    }
}
