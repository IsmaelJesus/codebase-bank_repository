package com.codecon.bank_project.Dtos;

import com.codecon.bank_project.Enum.CardTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentRequest(
        BigDecimal amount,
        LocalDate paymentDate,
        CardTypeEnum paymentType
) {
    public PaymentRequest{
        if (paymentDate == null)  paymentDate = LocalDate.now();
    }
}
