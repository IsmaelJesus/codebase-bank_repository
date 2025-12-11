package com.codecon.bank_project.Entity;

import com.codecon.bank_project.Exceptions.PaymentException;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard extends Card{
    private BigDecimal limitCard;
    private BigDecimal usedLimit = BigDecimal.ZERO;

    @Builder
    public CreditCard(Long cardNumber,int securityCode,BigDecimal limitCard,String password,Account account){
        super(cardNumber,securityCode,password, account);
        this.limitCard = limitCard;
    }

    public void applyPayment(BigDecimal amount) {
        Objects.requireNonNull(amount);

        if (!this.status) {
            throw new IllegalStateException("This card is deactivated");
        }

        BigDecimal totalPayment = this.usedLimit.add(amount);

        if(totalPayment.compareTo(limitCard) > 0) {
            throw new PaymentException("The payment amount exceeds the card limit");
        }

        this.usedLimit = totalPayment;
    }

    public BigDecimal invoice(){
        return this.usedLimit;
    }

    public void InvoicePayment(BigDecimal amount){
       this.usedLimit = usedLimit.subtract(amount);
    }
}
