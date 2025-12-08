package com.codecon.bank_project.Dtos;

import com.codecon.bank_project.Entity.Client;

import java.math.BigDecimal;
import java.util.UUID;

public record CurrentAccountRequest(
        Long agency,
        Long number,
        BigDecimal balance,
        UUID client_id
) {
}
