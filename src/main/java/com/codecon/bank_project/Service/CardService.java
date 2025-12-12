package com.codecon.bank_project.Service;

import com.codecon.bank_project.Dtos.*;
import com.codecon.bank_project.Entity.Account;
import com.codecon.bank_project.Entity.Card;
import com.codecon.bank_project.Entity.CreditCard;
import com.codecon.bank_project.Entity.DebitCard;
import com.codecon.bank_project.Enum.CardTypeEnum;
import com.codecon.bank_project.Exceptions.AccountNotFoundException;
import com.codecon.bank_project.Exceptions.CardNotFoundException;
import com.codecon.bank_project.Exceptions.PaymentException;
import com.codecon.bank_project.Interfaces.ICardResponse;
import com.codecon.bank_project.Repository.AccountRepository;
import com.codecon.bank_project.Repository.CardRepository;
import com.codecon.bank_project.Utils.CreditLimitCalculator;
import com.codecon.bank_project.Utils.CreditPayment;
import com.codecon.bank_project.mapper.CreditCardMapper;
import com.codecon.bank_project.mapper.DebitCardMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    CreditLimitCalculator creditLimitCalculator;
    @Autowired
    CreditPayment creditPayment;

    @Transactional
    public void create(CardRequest cardRequest){
        if(!cardRequest.password().equals(cardRequest.confirmationPassword())){
            throw new IllegalArgumentException("Password does not match");
        }

        Account account = accountRepository.findByNumberAndAgency(cardRequest.number(),cardRequest.agency())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        System.out.println(account);

        Card card;
        if (cardRequest.cardType() == CardTypeEnum.CREDIT){
            CreditCard creditCard = CreditCardMapper.cardToCreditCardEntity(cardRequest,account);

            BigDecimal limit = creditLimitCalculator.limitCalculator(account.getClient());
            creditCard.setLimitCard(limit);

            card = creditCard;
        }else{
            card = DebitCardMapper.cardToDebitCardEntity(cardRequest,account);
        }

        cardRepository.save(card);
    }

    public List<ICardResponse> findAll(){
        List<Card> cards = cardRepository.findAll();

        List<ICardResponse> listCard = new java.util.ArrayList<>();

        for (Card card : cards){
            if (card instanceof CreditCard){
                listCard.add(CreditCardMapper.toCreditCardResponse((CreditCard) card));
            }else {
                listCard.add(DebitCardMapper.toDebitCardResponse((DebitCard) card));
            }
        }

        return listCard;
    }

    public ICardResponse findCard(Long cardNumber){
        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new CardNotFoundException("Card not found"));

        if(card instanceof CreditCard){
            return CreditCardMapper.toCreditCardResponse((CreditCard) card);
        }else {
            return DebitCardMapper.toDebitCardResponse((DebitCard) card);
        }
    }

    @Transactional
    public void changeStatus(Long cardNumber){
        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new CardNotFoundException("Card not found"));

        card.changeStatus();

        cardRepository.save(card);
    }

    @Transactional
    public void changePassword(Long cardNumber, PasswordUpdateRequest passwordUpdateRequest){
        if (!passwordUpdateRequest.password().equals(passwordUpdateRequest.password_confirmation())){
            throw new IllegalArgumentException("Password does not match");
        }

        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new CardNotFoundException("Card not found"));

        card.setPassword(passwordUpdateRequest.password());

        cardRepository.save(card);
    }

    @Transactional
    public void Payment(Long cardNumber, PaymentRequest paymentRequest){
        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new CardNotFoundException("Card not found"));

        if (paymentRequest.paymentType().equals(CardTypeEnum.CREDIT)) {
            CreditCard creditCard;
            try {
                creditCard = (CreditCard) card;
            } catch (Exception e) {
                throw new IllegalArgumentException("This not is a Credit card");
            }
            BigDecimal amount = creditPayment.calculateFinalAmount(paymentRequest.amount(), creditCard.getLimitCard(), creditCard.getUsedLimit());

            creditCard.applyPayment(amount);

            cardRepository.save(creditCard);
        }else{
            DebitCard debitCard;
            try{
                debitCard = (DebitCard) card;
            }catch (Exception e){
                throw new IllegalArgumentException("This not is a Debit card");
            }

            if(debitCard.getAccount().getBalance().compareTo(paymentRequest.amount()) < 0){
                throw new PaymentException("The amount exceed your balance");
            }

            debitCard.canPay(paymentRequest.amount());

            debitCard.getAccount().withdraw(paymentRequest.amount());

            debitCard.registerUse();

            cardRepository.save(debitCard);
        }
    }

    @Transactional
    public void invoicePayment(Long cardNumber, InvoicePaymentRequest invoicePaymentRequest){
        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new CardNotFoundException("Card not found"));

        CreditCard creditCard;
        try{
            creditCard = (CreditCard) card;
        }catch (Exception e) {
            throw new IllegalArgumentException("This not is a credit card");
        }

        if(card.getAccount().getBalance().compareTo(invoicePaymentRequest.amount()) < 0){
            throw new IllegalArgumentException("The amount exceed your balance");
        }

        creditCard.InvoicePayment(invoicePaymentRequest.amount());
        creditCard.getAccount().withdraw(invoicePaymentRequest.amount());

        cardRepository.save(creditCard);
    }

    @Transactional
    public void changeLimitDiary(Long cardNumber, ChangeDebitLimit changeDebitLimit){
        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new CardNotFoundException("Card not found"));

        DebitCard debitCard;
        try{
            debitCard = (DebitCard) card;
        }catch (Exception e) {
            throw new IllegalArgumentException("This not is a debit card");
        }

        debitCard.limitAdjust(changeDebitLimit.limitDiary());

        cardRepository.save(debitCard);
    }
}
