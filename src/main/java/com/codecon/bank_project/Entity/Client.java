package com.codecon.bank_project.Entity;

import com.codecon.bank_project.Enum.ClientTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String cpf;
    private LocalDate dateBirth;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @Enumerated(EnumType.STRING)
    private ClientTypeEnum clientType;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Account> account;
}
