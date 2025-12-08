package com.codecon.bank_project.Entity;

import com.codecon.bank_project.Enum.ClientTypeEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("SAVINGS")
@Getter
@Setter
@NoArgsConstructor
public class SavingsAccount extends Account {
    private int quantityMonths;

    @Builder
    public SavingsAccount(Long id, Long number, Long agency, LocalDateTime createdAt, LocalDateTime updatedAt, Client client) {
        super(id, number, agency, createdAt, updatedAt, client);
    }

    public void creditIncoming(){
        double annualRate = (double) 0;
        double monthlyFactor = (double) 0;

        if(client.getClientType() == ClientTypeEnum.COMMON){
            annualRate = 0.005;
            monthlyFactor = Math.pow(1 + annualRate, 1.0 / 12.0) - 1;

            applyInterest(annualRate);
        } else if (client.getClientType() == ClientTypeEnum.SUPER) {
            annualRate = 0.007;
            monthlyFactor = Math.pow(1 + annualRate, 1.0 / 12.0) - 1;
        }else{
            annualRate = 0.009;
            monthlyFactor = Math.pow(1 + annualRate, 1.0 / 12.0) - 1;
        }

        applyInterest(monthlyFactor);
    }

    protected void applyInterest(double monthlyRate) {
        // Converte double para BigDecimal
        BigDecimal rateBD = BigDecimal.valueOf(monthlyRate);

        // 1. Calcula quanto rendeu e ARREDONDA O RENDIMENTO AGORA
        // Isso garante que o valor creditado tenha apenas 2 casas decimais (centavos reais)
        BigDecimal earnings = this.balance.multiply(rateBD)
                .setScale(2, RoundingMode.HALF_EVEN);

        // 2. Soma o valor já arredondado ao saldo
        this.balance = this.balance.add(earnings);
    }
}
