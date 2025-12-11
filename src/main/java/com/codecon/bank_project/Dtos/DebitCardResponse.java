package com.codecon.bank_project.Dtos;

import com.codecon.bank_project.Interfaces.ICardDebitResponse;

public record DebitCardResponse(
        Long id,
        Long cardNumber,
        boolean status,
        Long number,
        Long agency,
        int dailyLimit,
        int dailyUses
) implements ICardDebitResponse {
}
