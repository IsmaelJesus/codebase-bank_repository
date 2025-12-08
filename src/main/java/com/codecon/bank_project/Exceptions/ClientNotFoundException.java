package com.codecon.bank_project.Exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String message){
        super(message);
    }
}
