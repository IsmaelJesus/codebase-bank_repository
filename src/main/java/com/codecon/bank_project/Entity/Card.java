package com.codecon.bank_project.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected Long cardNumber;
    protected int securityCode;
    protected String password;
    protected boolean status;
    @ManyToOne
    @JoinColumn(name = "account_id",  nullable = false)
    @ToString.Exclude
    protected Account account;

    public Card(Long cardNumber,int securityCode,String password,Account account){
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.password = password;
        this.account = account;
        this.status = true;
    }

    public void changeStatus(){
        if(this.status){
            this.status = false;
        }else {
            this.status = true;
        }
    }

    public void changePassword(String password){
        this.password = password;
    }
}
