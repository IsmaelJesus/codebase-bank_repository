package com.codecon.bank_project.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
//Allows the user, to have only one account of the each type
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
    @NotNull(message = "The account number cannot be empty")
    protected Long number;
    @NotNull(message = "The account agency cannot be empty")
    protected Long agency;
    protected BigDecimal balance =  BigDecimal.ZERO;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    @ManyToOne
    @NotNull(message = "The account client cannot be empty")
    protected Client client;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @ToString.Exclude
    protected List<Card> cards;

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
