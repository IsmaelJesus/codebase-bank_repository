package com.codecon.bank_project.Dtos;

import java.math.BigDecimal;

public record TransferenceRequest(
        Long payerNumberAgency,
        Long payerNumberAccount,
        Long payeeNumberAgency,
        Long payeeNumberAccount,
        BigDecimal amount
) {
}
