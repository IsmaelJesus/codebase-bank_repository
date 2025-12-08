package com.codecon.bank_project.Dtos;

import com.codecon.bank_project.Interfaces.IAccountResponse;

import java.math.BigDecimal;
import java.util.UUID;

public record CurrentAccountResponse(
        Long id,
        Long number,
        Long agency,
        BigDecimal balance,
        UUID client_id
) implements IAccountResponse{
}
