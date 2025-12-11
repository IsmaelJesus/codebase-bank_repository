package com.codecon.bank_project.Dtos;

import com.codecon.bank_project.Enum.CardTypeEnum;

import java.math.BigDecimal;

public record CardResponse(
        Long id,
        Long cardNumber,
        int securityCode,
        String password,
        String confirmationPassword,
        BigDecimal limitCard,
        int dailyLimit,
        Long number,
        Long agency,
        CardTypeEnum cardType
) {
}
