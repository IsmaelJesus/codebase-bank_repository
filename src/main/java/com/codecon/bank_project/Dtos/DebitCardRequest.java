package com.codecon.bank_project.Dtos;

public record DebitCardRequest(
        Long cardNumber,
        int securityCode,
        String password,
        String confirmationPassword,
        int dailyLimit,
        Long account_id
) {
}
