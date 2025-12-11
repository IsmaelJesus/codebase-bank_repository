package com.codecon.bank_project.mapper;

import com.codecon.bank_project.Dtos.*;
import com.codecon.bank_project.Entity.Account;
import com.codecon.bank_project.Entity.CreditCard;
import com.codecon.bank_project.Entity.DebitCard;

public class DebitCardMapper {
    public static DebitCard toCreditCardEntity(DebitCardRequest debitCardRequest, Account account){
        return DebitCard.builder()
                .cardNumber(debitCardRequest.cardNumber())
                .securityCode(debitCardRequest.securityCode())
                .dailyLimit(debitCardRequest.dailyLimit())
                .password(debitCardRequest.password())
                .account(account)
                .build();
    }

    public static DebitCard cardToDebitCardEntity(CardRequest cardRequest, Account account){
        return DebitCard.builder()
                .cardNumber(cardRequest.cardNumber())
                .password(cardRequest.password())
                .securityCode(cardRequest.securityCode())
                .dailyLimit(cardRequest.dailyLimit())
                .account(account)
                .build();
    }

    public static DebitCardResponse toDebitCardResponse(DebitCard debitCard){
        return new DebitCardResponse(
                debitCard.getId(),
                debitCard.getCardNumber(),
                debitCard.isStatus(),
                debitCard.getAccount().getNumber(),
                debitCard.getAccount().getAgency(),
                debitCard.getDailyLimit(),
                debitCard.getDailyUseQuantity()
        );
    }
}
