package com.codecon.bank_project.Dtos;

import java.math.BigDecimal;

public record CreditCardRequest(
        Long cardNumber,
        int securityCode,
        String password,
        String confirmationPassword,
        BigDecimal limitCard,
        Long account_id
) {
}
