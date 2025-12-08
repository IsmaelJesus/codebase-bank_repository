package com.codecon.bank_project.Interfaces;

import java.math.BigDecimal;
import java.util.UUID;

public interface IAccountResponse {
    Long id();
    Long number();
    Long agency();
    BigDecimal balance();
    UUID client_id();
}
