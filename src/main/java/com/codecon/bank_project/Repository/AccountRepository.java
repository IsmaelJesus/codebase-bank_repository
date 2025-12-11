package com.codecon.bank_project.Repository;

import com.codecon.bank_project.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Optional<Account> findByNumberAndAgency(Long number, Long agency);
}
