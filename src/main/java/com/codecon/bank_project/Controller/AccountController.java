package com.codecon.bank_project.Controller;

import com.codecon.bank_project.Dtos.*;
import com.codecon.bank_project.Entity.Account;
import com.codecon.bank_project.Interfaces.IAccountResponse;
import com.codecon.bank_project.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public List<AccountResponse> getAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/{number}/{agency}/balance")
    public void balance(@PathVariable Long number, @PathVariable Long agency) {
        accountService.getBalance(number,agency);
    }

    @GetMapping("/number/{number}/{agency}")
    public IAccountResponse findByNumber(@PathVariable Long number, @PathVariable Long agency){
        return accountService.getByAccountNumber(number,agency);
    }

    @PostMapping
    public IAccountResponse create(@RequestBody AccountRequest accountRequest){

        return accountService.create(accountRequest);
    }

    @PostMapping("/{number}/{agency}/deposit")
    public void deposit(@PathVariable Long number,@PathVariable Long agency, @RequestBody DepositRequest depositRequest){
        accountService.deposit(number, agency, depositRequest);
    }

    @PostMapping("/{number}/{agency}/withdraw")
    public void withdraw(@PathVariable Long number, @PathVariable Long agency, @RequestBody WithdrawRequest withdrawRequest){
        accountService.withdraw(number,agency, withdrawRequest);
    }

    @PostMapping("/transference")
    public void transference(@RequestBody TransferenceRequest transferenceRequest){
        accountService.transference(transferenceRequest);
    }

    @PutMapping("/{number}/{agency}")
    public IAccountResponse update(@PathVariable Long number, @PathVariable Long agency, @RequestBody AccountRequest accountRequest){
        return accountService.update(number,agency,accountRequest);
    }

    @PutMapping("/incoming/{number}/{agency}")
    public void creditIncoming(@PathVariable Long number, @PathVariable Long agency){
        accountService.creditIncoming(number,agency);
    }

    @PutMapping("/fee/{number}/{agency}")
    public void maintenceFee(@PathVariable Long number, @PathVariable Long agency){
        accountService.maintenanceFee(number,agency);
    }

    @DeleteMapping("/{number}/{agency}")
    public void delete(@PathVariable Long number, @PathVariable Long agency){
        accountService.delete(number,agency);
    }
}
