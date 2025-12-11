package com.codecon.bank_project.Utils;

import com.codecon.bank_project.Entity.Client;
import com.codecon.bank_project.Enum.ClientTypeEnum;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreditLimitCalculator {
    public BigDecimal limitCalculator(Client client){
        if(client.getClientType().equals(ClientTypeEnum.COMMON)){
            return new BigDecimal(1000);
        }else if(client.getClientType().equals(ClientTypeEnum.SUPER)){
            return new BigDecimal(5000);
        }else if(client.getClientType().equals(ClientTypeEnum.PREMIUM)){
            return new BigDecimal(10000);
        }

        throw new IllegalArgumentException("Client type invalid");
    }
}
