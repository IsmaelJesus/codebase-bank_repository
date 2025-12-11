package com.codecon.bank_project.Entity;

import com.codecon.bank_project.Exceptions.PaymentException;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebitCard extends Card{
    private int dailyLimit;
    private int dailyUseQuantity;
    private LocalDate lastUseDate;

    @Builder
    public DebitCard(Long cardNumber,int securityCode,int dailyLimit,String password,Account account){
        super(cardNumber,securityCode,password,account);
        this.dailyUseQuantity = 0;
        this.lastUseDate = null;
    }

    public void canPay(BigDecimal amount) {
        if (!this.status) {
            throw new IllegalArgumentException("This card is deactivated");
        }

       if((lastUseDate != null && lastUseDate.isEqual(LocalDate.now())) && dailyLimit <= dailyUseQuantity){
           throw new PaymentException("The daily payment limit has been reached");
       }
    }

    public void registerUse(){
        this.dailyUseQuantity++;
        this.lastUseDate = LocalDate.now();
    }

    public void limitAdjust(int newLimit) {
        if (this.dailyUseQuantity + 1 > this.dailyLimit) {
            throw new IllegalArgumentException("The new limit is minor of the your actual limit uses");
        }

        LocalDate dataAtual = LocalDate.now();

        if (lastUseDate == null || !lastUseDate.equals(dataAtual)) {
            this.dailyUseQuantity = 0;
            this.lastUseDate = dataAtual;
        }

        this.dailyUseQuantity += 1;
        this.lastUseDate = LocalDate.now();
    }
}
