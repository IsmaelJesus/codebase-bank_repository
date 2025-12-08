package com.codecon.bank_project.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The street cannot be empty")
    private String street;
    @NotNull(message = "The number cannot be empty")
    @Min(value = 1, message = "The number must be positive")
    private Integer number;
    @NotBlank(message = "The city cannot be empty")
    private String city;
    @NotBlank(message = "The state cannot be empty")
    private String state;
    @NotBlank(message = "The zip code cannot be empty")
    private String zipCode;
    @OneToOne(mappedBy = "address")
    private Client client;
}
