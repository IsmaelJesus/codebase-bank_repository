package com.codecon.bank_project.Dtos;

import com.codecon.bank_project.Entity.Client;
import com.codecon.bank_project.Enum.AccountTypeEnum;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountResponse(
        Long id,
        Long number,
        Long agency,
        BigDecimal balance,
        UUID client
) {
}
