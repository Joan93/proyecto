package com.joan.florit.domain.exception;

public class PriceNotFoundException extends RuntimeException{
    
    public PriceNotFoundException(String msg){
        super(msg);
    }
}
