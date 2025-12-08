package com.codecon.bank_project.Dtos;

import com.codecon.bank_project.Entity.Client;

import java.math.BigDecimal;
import java.util.UUID;

public record SavingsAccountRequest(
        Long number,
        Long agency,
        BigDecimal balance,
        UUID client_id
) {
}