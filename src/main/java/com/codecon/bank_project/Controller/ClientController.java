package com.codecon.bank_project.Controller;

import com.codecon.bank_project.Dtos.ClientRequest;
import com.codecon.bank_project.Dtos.ClientResponse;
import com.codecon.bank_project.Service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public List<ClientResponse> list(){
        return clientService.findAll();
    }

    @GetMapping("/id/{id}")
    public ClientResponse findById(@PathVariable UUID id){
        return clientService.findById(id);
    }

    @GetMapping("/cpf/{cpf}")
    public ClientResponse findByCpf(@PathVariable String cpf){
        return clientService.findByCpf(cpf);
    }

    @PostMapping
    public ClientResponse create(@Valid @RequestBody ClientRequest clientRequest){
        return clientService.save(clientRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        clientService.delete(id);
        return ResponseEntity.ok("Cliente removido com sucesso.");
    }

    @PutMapping("/{id}")
    public ClientResponse update(@PathVariable UUID id, @Valid @RequestBody ClientRequest clientRequest){
        return clientService.update(id,clientRequest);
    }
}
