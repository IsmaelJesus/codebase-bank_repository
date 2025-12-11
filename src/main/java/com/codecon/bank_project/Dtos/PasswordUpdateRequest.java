package com.codecon.bank_project.Dtos;

public record PasswordUpdateRequest (
        String password,
        String password_confirmation
){
}
