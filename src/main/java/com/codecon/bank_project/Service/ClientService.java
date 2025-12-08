package com.codecon.bank_project.Service;

import java.util.List;
import java.util.UUID;

import com.codecon.bank_project.Exceptions.InvalidInformationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codecon.bank_project.Dtos.ClientRequest;
import com.codecon.bank_project.Dtos.ClientResponse;
import com.codecon.bank_project.Entity.Address;
import com.codecon.bank_project.Entity.Client;
import com.codecon.bank_project.Exceptions.ClientNotFoundException;
import com.codecon.bank_project.Repository.ClientRepository;
import com.codecon.bank_project.mapper.AddressMapper;
import com.codecon.bank_project.mapper.ClientMapper;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public ClientResponse save(ClientRequest clientRequest){

        if (!Utils.Utils.cpfValidator(clientRequest.cpf())) throw new InvalidInformationException("The cpf provided is invalid");

        //Validate the date birth
        Utils.Utils.dateBrithValidate(clientRequest.dateBirth());

        Address address = AddressMapper.clientRequestToEntity(clientRequest);

        Client client = ClientMapper.toEntity(clientRequest);

        client.setAddress(address);

        client = clientRepository.save(client);

        return ClientMapper.toDto(client);
    }

    public List<ClientResponse> findAll(){
        List<Client> clients = clientRepository.findAll();

        List<ClientResponse> clientResponses = clients.stream().map(ClientMapper::toDto).toList();

        return clientResponses;
    }

    public ClientResponse findById(UUID id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));

        return ClientMapper.toDto(client);
    }

    public ClientResponse findByCpf(String cpf){
        Client client = clientRepository.findByCpf(cpf).orElseThrow(() -> new ClientNotFoundException("Cpf not found"));

        return ClientMapper.toDto(client);
    }

    public void delete(UUID id){
        clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));

        clientRepository.deleteById(id);
    }

    public ClientResponse update(UUID id, ClientRequest clientRequest){

        if (!Utils.Utils.cpfValidator(clientRequest.cpf())) throw new IllegalArgumentException("The cpf provided is invalid");

        //Validate the date birth
        Utils.Utils.dateBrithValidate(clientRequest.dateBirth());

        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));

        client = ClientMapper.toUpdate(client, clientRequest);
        
        client = clientRepository.save(client);

        return  ClientMapper.toDto(client);
    }
}
