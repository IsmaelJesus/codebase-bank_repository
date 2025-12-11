package com.codecon.bank_project.Controller;

import com.codecon.bank_project.Dtos.*;
import com.codecon.bank_project.Entity.Card;
import com.codecon.bank_project.Interfaces.ICardResponse;
import com.codecon.bank_project.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping
    public List<ICardResponse> findAll(){
        return cardService.findAll();
    }

    @GetMapping("/{card}")
    public ICardResponse findCard(@PathVariable Long card){
        return cardService.findCard(card);
    }

    @PostMapping
    public void create(@RequestBody CardRequest cardRequest){
        cardService.create(cardRequest);
    }

    @PostMapping("{card}/payment/")
    public void pay(@PathVariable Long card, @RequestBody PaymentRequest paymentRequest){
        cardService.Payment(card, paymentRequest);
    }

    @PutMapping("/{cardNumber}/status")
    public void changeStatus(@PathVariable Long cardNumber){
        cardService.changeStatus(cardNumber);
    }

    @PutMapping("/{card}/password")
    public void changePassword(@PathVariable Long card, @RequestBody PasswordUpdateRequest passwordUpdateRequest){
        cardService.changePassword(card, passwordUpdateRequest);
    }

    @PostMapping("/{card}/invoice/payment")
    public void invoicePayment(@PathVariable Long card, @RequestBody InvoicePaymentRequest invoicePaymentRequest){
        cardService.invoicePayment(card, invoicePaymentRequest);
    }

    @PutMapping("/{card}/diary-limit")
    public void changeLimitDiary(@PathVariable Long card, @RequestBody ChangeDebitLimit changeDebitLimit){
        cardService.changeLimitDiary(card, changeDebitLimit);
    }
}
