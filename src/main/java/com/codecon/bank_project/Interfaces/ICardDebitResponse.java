package com.codecon.bank_project.Interfaces;

public interface ICardDebitResponse extends ICardResponse{
    int dailyLimit();
    int dailyUses();
}
