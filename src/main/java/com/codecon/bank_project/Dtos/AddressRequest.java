package com.codecon.bank_project.Dtos;

public record AddressRequest(
       String street,
       int number,
       String city,
       String state,
       String zipCode
) {
}
