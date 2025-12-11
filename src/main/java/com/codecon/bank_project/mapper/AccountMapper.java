package com.codecon.bank_project.mapper;

import com.codecon.bank_project.Dtos.AccountRequest;
import com.codecon.bank_project.Dtos.CurrentAccountResponse;
import com.codecon.bank_project.Dtos.SavingsAccountResponse;
import com.codecon.bank_project.Entity.Account;
import com.codecon.bank_project.Entity.Client;
import com.codecon.bank_project.Entity.CurrentAccount;
import com.codecon.bank_project.Entity.SavingsAccount;

import java.time.LocalDateTime;
import java.util.Objects;

public class AccountMapper {
    public static CurrentAccount toCurrentEntity(AccountRequest accountRequest, Client client) {
        return CurrentAccount.builder()
                .number(accountRequest.number())
                .agency(accountRequest.agency())
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .client(client)
                .build();
    }

    public static SavingsAccount toSavingsEntity(AccountRequest accountRequest, Client client) {
        return SavingsAccount.builder()
                .number(accountRequest.number())
                .agency(accountRequest.agency())
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .client(client)
                .build();
    }

    public static CurrentAccountResponse toCurrentResponse(Account account){
        return new CurrentAccountResponse(
                account.getId(),
                account.getNumber(),
                account.getAgency(),
                account.getBalance(),
                account.getClient().getId()
                );
    }

    public static SavingsAccountResponse toSavingsResponse(Account account){
        return new SavingsAccountResponse(
                account.getId(),
                account.getNumber(),
                account.getAgency(),
                account.getBalance(),
                account.getClient().getId()
        );
    }

    public static Account toUpdate(Account account, AccountRequest accountRequest){
        if(!Objects.equals(account.getNumber(), accountRequest.number())){
            account.setNumber(accountRequest.number());
        }

        if(!Objects.equals(account.getAgency(), accountRequest.agency())){
            account.setAgency(accountRequest.agency());
        }

        if(!Objects.equals(account.getBalance(), accountRequest.balance())){
            account.setBalance(accountRequest.balance());
        }

        return account;
    }
}
