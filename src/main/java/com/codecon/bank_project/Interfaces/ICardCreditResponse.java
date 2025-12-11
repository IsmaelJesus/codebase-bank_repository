package com.codecon.bank_project.Interfaces;

import java.math.BigDecimal;

public interface ICardCreditResponse extends ICardResponse{
    BigDecimal limit();
    BigDecimal usedLimit();
}
