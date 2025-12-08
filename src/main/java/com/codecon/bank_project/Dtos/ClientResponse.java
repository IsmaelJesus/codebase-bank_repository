package com.codecon.bank_project.Dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.codecon.bank_project.Entity.Address;
import com.codecon.bank_project.Enum.ClientTypeEnum;

public record ClientResponse(
        UUID id,
        String name,
        String cpf,
        LocalDate date,
        Address address,
        ClientTypeEnum clientType
) {
}
