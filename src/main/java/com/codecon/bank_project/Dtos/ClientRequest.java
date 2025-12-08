package com.codecon.bank_project.Dtos;

import com.codecon.bank_project.Entity.Address;
import com.codecon.bank_project.Enum.ClientTypeEnum;
import jakarta.validation.Valid;

import java.time.LocalDate;

public record ClientRequest(
        String name,
        String cpf,
        LocalDate dateBirth,
        Address address,
        ClientTypeEnum clientType
) {
}
