package com.codecon.bank_project.Repository;

import com.codecon.bank_project.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    public Optional<Card> findByCardNumber(Long cardNumber);
}
