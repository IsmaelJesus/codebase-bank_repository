package com.codecon.bank_project.Service;

import com.codecon.bank_project.Dtos.*;
import com.codecon.bank_project.Entity.Account;
import com.codecon.bank_project.Entity.Client;
import com.codecon.bank_project.Entity.CurrentAccount;
import com.codecon.bank_project.Entity.SavingsAccount;
import com.codecon.bank_project.Enum.AccountTypeEnum;
import com.codecon.bank_project.Exceptions.AccountNotFoundException;
import com.codecon.bank_project.Exceptions.ClientNotFoundException;
import com.codecon.bank_project.Interfaces.IAccountResponse;
import com.codecon.bank_project.Repository.AccountRepository;
import com.codecon.bank_project.Repository.ClientRepository;
import com.codecon.bank_project.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService extends Account {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    public IAccountResponse create(AccountRequest accountRequest) {
        Client client = clientRepository.
                findById(accountRequest.client_id()).orElseThrow(() -> new ClientNotFoundException("Client not found"));

        System.out.println(accountRequest);

        Account account;
        if(accountRequest.accountType() == AccountTypeEnum.CURRENT){
            account = new CurrentAccount();
            account = AccountMapper.toCurrentEntity(accountRequest, client);
            account =  accountRepository.save(account);

            return AccountMapper.toCurrentResponse(account);
        }else if(accountRequest.accountType() == AccountTypeEnum.SAVINGS){
            account = new SavingsAccount();
            account = AccountMapper.toSavingsEntity(accountRequest,client);
            account =  accountRepository.save(account);

            return AccountMapper.toSavingsResponse(account);
        }

        return null;
    }

    public List<AccountResponse> findAll() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream().map(account -> new AccountResponse(
                account.getId(),
                account.getNumber(),
                account.getAgency(),
                account.getBalance(),
                account.getClient().getId()
        )).toList();
    }

    public IAccountResponse getByAccountNumber(Long number, Long agency) {
        Account account = accountRepository.getByNumberAndAgency(number, agency).orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if(account instanceof CurrentAccount){
            return AccountMapper.toCurrentResponse(account);
        }else if(account instanceof SavingsAccount){
            return AccountMapper.toSavingsResponse(account);
        }

        throw new IllegalArgumentException("Account not found");
    }

    public IAccountResponse update(Long number,Long agency, AccountRequest accountRequest){
        Account account = accountRepository.getByNumberAndAgency(number, agency)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account = AccountMapper.toUpdate(account,accountRequest);

        account =  accountRepository.save(account);

        System.out.println(account);

        if(account instanceof CurrentAccount){
            return AccountMapper.toCurrentResponse(account);
        }else if(account instanceof SavingsAccount){
            return AccountMapper.toSavingsResponse(account);
        }

        throw new IllegalArgumentException("Account type not valid");
    }

    public void delete(Long number,Long agency) {
        Account account = accountRepository.getByNumberAndAgency(number,agency).orElseThrow(() -> new AccountNotFoundException("Account not found"));

        accountRepository.delete(account);
    }

    public BigDecimal getBalance(Long number, Long agency){
        Account account = accountRepository.getByNumberAndAgency(number,agency)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        return account.getBalance();
    }

    public void deposit(Long number, Long agency, DepositRequest  depositRequest) {
        Account account =  accountRepository.getByNumberAndAgency(number,agency)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.deposit(depositRequest.amount());

        accountRepository.save(account);
    }

    public void withdraw(Long number, Long agency, WithdrawRequest  withdrawRequest){
        Account account =  accountRepository.getByNumberAndAgency(number,agency)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.withdraw(withdrawRequest.amount());

        accountRepository.save(account);
    }

    public void transference(TransferenceRequest transferenceRequest) {
        if(transferenceRequest.amount() == null){
            throw new IllegalArgumentException("Amount is null");
        }

        Account accountPayer = accountRepository.getByNumberAndAgency(transferenceRequest.payerNumberAccount(),transferenceRequest.payerNumberAgency()).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        Account accountPayee = accountRepository.getByNumberAndAgency(transferenceRequest.payeeNumberAccount(),transferenceRequest.payeeNumberAgency()).orElseThrow(() -> new AccountNotFoundException("Account not found"));

        accountPayer.withdraw(transferenceRequest.amount());
        accountPayee.deposit(transferenceRequest.amount());

        accountRepository.save(accountPayer);
        accountRepository.save(accountPayee);
    }

    public void creditIncoming(Long  number, Long agency){
        Account account = accountRepository.getByNumberAndAgency(number,agency).orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if(account instanceof SavingsAccount){
            Client client = clientRepository.findById(account.getClient().getId()).orElseThrow(() -> new AccountNotFoundException("Client not found"));

            ((SavingsAccount) account).creditIncoming();

            accountRepository.save(account);
        }
    }
    public void maintenanceFee(Long  number, Long agency){
        Account account = accountRepository.getByNumberAndAgency(number,agency).orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if(account instanceof CurrentAccount){
            Client client = clientRepository.findById(account.getClient().getId()).orElseThrow(() -> new AccountNotFoundException("Client not found"));

            ((CurrentAccount) account).maintenanceFee();

            accountRepository.save(account);
        }
    }
}
