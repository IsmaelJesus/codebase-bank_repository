package com.codecon.bank_project.mapper;

import com.codecon.bank_project.Dtos.AddressRequest;
import com.codecon.bank_project.Dtos.AddressResponse;
import com.codecon.bank_project.Dtos.ClientRequest;
import com.codecon.bank_project.Entity.Address;

public class AddressMapper {

    public static Address toEntity(AddressRequest addressRequest){
        return Address.builder()
                .street(addressRequest.street())
                .number(addressRequest.number())
                .city(addressRequest.city())
                .state(addressRequest.state())
                .zipCode(addressRequest.zipCode())
                .build();
    }

    public static Address clientRequestToEntity(ClientRequest clientRequest){
        return Address.builder()
                .street(clientRequest.address().getStreet())
                .number(clientRequest.address().getNumber())
                .city(clientRequest.address().getCity())
                .state(clientRequest.address().getState())
                .zipCode(clientRequest.address().getZipCode())
                .build();
    }

    public static AddressResponse toDto(Address address){
        return new AddressResponse(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getCity(),
                address.getState(),
                address.getZipCode()
        );
    }

    public static Address toUpdate(Address address, ClientRequest clientRequest){
        if(address.getStreet().equals(clientRequest.address().getStreet())){
            address.setStreet(clientRequest.address().getStreet());
        }

        if(address.getNumber() ==  clientRequest.address().getNumber()){
            address.setNumber(clientRequest.address().getNumber());
        }

        if(address.getCity().equals(clientRequest.address().getCity())){
            address.setCity(clientRequest.address().getCity());
        }

        if(address.getState().equals(clientRequest.address().getState())){
            address.setState(clientRequest.address().getState());
        }

        if(address.getZipCode() ==  clientRequest.address().getZipCode()){
            address.setZipCode(clientRequest.address().getZipCode());
        }

        return address;
    }
}
