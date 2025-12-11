package com.codecon.bank_project.Utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Component
public class CreditPayment {
    private static final BigDecimal THRESHOLD = BigDecimal.valueOf(0.8);
    private static final BigDecimal FEE = BigDecimal.valueOf(0.05);

    public BigDecimal calculateFinalAmount(BigDecimal amount, BigDecimal limit, BigDecimal used) {

        Objects.requireNonNull(amount);
        Objects.requireNonNull(used);
        Objects.requireNonNull(limit);

        BigDecimal usagePercentage = used.add(amount)
                .divide(limit, 4, RoundingMode.HALF_UP);

        if (usagePercentage.compareTo(THRESHOLD) > 0) {
            return amount.add(amount.multiply(FEE));
        }

        return amount;
    }
}
