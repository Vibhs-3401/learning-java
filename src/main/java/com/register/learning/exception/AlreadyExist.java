package com.register.learning.exception;

public class AlreadyExist extends RuntimeException{

    public AlreadyExist(String message){
        super(message);
    }
}
