package com.codecon.bank_project.mapper;

import com.codecon.bank_project.Dtos.CardRequest;
import com.codecon.bank_project.Dtos.CreditCardRequest;
import com.codecon.bank_project.Entity.Account;
import com.codecon.bank_project.Entity.CreditCard;

public class CardMapper {
    public static CreditCard toCardEntity(CardRequest cardRequest, Account account){
        return CreditCard.builder()
                .cardNumber(cardRequest.cardNumber())
                .securityCode(cardRequest.securityCode())
                .limitCard(cardRequest.limitCard())
                .password(cardRequest.password())
                .account(account)
                .build();
    }
}
