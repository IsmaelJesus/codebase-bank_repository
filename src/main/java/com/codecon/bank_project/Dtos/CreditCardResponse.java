package com.codecon.bank_project.Dtos;

import com.codecon.bank_project.Interfaces.ICardCreditResponse;

import java.math.BigDecimal;

public record CreditCardResponse(
        Long id,
        Long cardNumber,
        boolean status,
        Long number,
        Long agency,
        BigDecimal limit,
        BigDecimal usedLimit
) implements ICardCreditResponse {
}
