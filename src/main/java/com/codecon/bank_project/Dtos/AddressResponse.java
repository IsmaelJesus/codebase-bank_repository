package com.codecon.bank_project.Dtos;

public record AddressResponse(
        Long id,
        String street,
        int number,
        String city,
        String state,
        String zipCode
) {
}
