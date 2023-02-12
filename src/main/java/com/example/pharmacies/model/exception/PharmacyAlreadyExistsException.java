package com.example.pharmacies.model.exception;

public class PharmacyAlreadyExistsException extends Exception{
    public PharmacyAlreadyExistsException(String message){
        super(message);
    }
}
