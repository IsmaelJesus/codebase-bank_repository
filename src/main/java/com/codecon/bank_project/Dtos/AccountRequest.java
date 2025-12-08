package com.codecon.bank_project.Dtos;

import com.codecon.bank_project.Entity.Client;
import com.codecon.bank_project.Enum.AccountTypeEnum;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AccountRequest(
        Long number,
        Long agency,
        BigDecimal balance,
        AccountTypeEnum accountType,
        UUID client_id) {
}
