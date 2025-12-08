package com.codecon.bank_project.mapper;

import com.codecon.bank_project.Dtos.ClientRequest;
import com.codecon.bank_project.Dtos.ClientResponse;
import com.codecon.bank_project.Entity.Client;

public class ClientMapper {

    public static Client toEntity(ClientRequest clientRequest){
        return  Client.builder()
                .name(clientRequest.name())
                .cpf(clientRequest.cpf())
                .dateBirth(clientRequest.dateBirth())
                .address(clientRequest.address())
                .clientType(clientRequest.clientType())
                .build();

    }

    public static ClientResponse toDto(Client client){
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getCpf(),
                client.getDateBirth(),
                client.getAddress(),
                client.getClientType()
        );
    }

    public static Client toUpdate(Client client, ClientRequest clientRequest){
        if(client.getName() == null || !client.getName().equals(clientRequest.name())){
            client.setName(clientRequest.name());
        }

        if(client.getCpf() == null || !client.getCpf().equals(clientRequest.cpf())){
            client.setCpf(clientRequest.cpf());
        }

        if(client.getDateBirth() == null || !client.getDateBirth().equals(clientRequest.dateBirth())){
            client.setDateBirth(clientRequest.dateBirth());
        }

        if(client.getAddress() == null || !client.getAddress().equals(clientRequest.address())){
            client.setAddress(clientRequest.address());
        }

        if(client.getClientType() == null || !client.getClientType().equals(clientRequest.clientType())){
            client.setClientType(clientRequest.clientType());
        }

        return  client;
    }
}
