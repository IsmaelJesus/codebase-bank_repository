package com.codecon.bank_project.Entity;

import com.codecon.bank_project.Enum.ClientTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
    @NotBlank(message = "The name is mandatory")
    private String name;
    @NotBlank(message = "The cpf is mandatory")
    @Column(unique = true)
    private String cpf;
    @NotNull(message =  "The birth date is mandatory")
    @Past
    private LocalDate dateBirth;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @Enumerated(EnumType.STRING)
    private ClientTypeEnum clientType;
    @ToString.Exclude
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Account> account;
}
