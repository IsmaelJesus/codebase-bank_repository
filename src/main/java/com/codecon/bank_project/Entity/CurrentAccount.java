package com.codecon.bank_project.Entity;

import com.codecon.bank_project.Enum.ClientTypeEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CURRENT")
@Getter
@Setter
@NoArgsConstructor
public class CurrentAccount extends Account {

    @Builder
    public CurrentAccount(Long id, Long number, Long agency, LocalDateTime createdAt, LocalDateTime updatedAt, Client client) {
        super(id, number, agency, createdAt, updatedAt, client);
    }

    public void maintenanceFee(){
        if(client.getClientType() == ClientTypeEnum.COMMON){
            this.balance = this.balance.subtract(BigDecimal.valueOf(12));
        } else if (client.getClientType() == ClientTypeEnum.SUPER) {
            this.balance = this.balance.subtract(BigDecimal.valueOf(8));
        }
    }
}
