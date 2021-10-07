package com.jlab.ab.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String s){
        super(s);
    }
}
