package com.codecon.bank_project.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"client_id", "account_type"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(unique = true)
    protected Long number;
    protected Long agency;
    protected BigDecimal balance =  BigDecimal.ZERO;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    @ManyToOne
    protected Client client;

    public Account(Long id, Long number, Long agency, LocalDateTime createdAt, LocalDateTime updatedAt, Client client){
        this.id = id;
        this.number = number;
        this.agency = agency;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.client = client;
    }

    public void withdraw(BigDecimal amount){
       this.balance = this.balance.subtract(amount);
    }

    public void deposit(BigDecimal amount){
        this.balance = this.balance.add(amount);
    }
}
