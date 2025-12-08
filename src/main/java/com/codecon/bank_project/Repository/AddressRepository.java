package com.codecon.bank_project.Repository;

import com.codecon.bank_project.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
