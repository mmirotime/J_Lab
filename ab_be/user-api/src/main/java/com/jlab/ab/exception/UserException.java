package com.jlab.ab.exception;

import com.jlab.ab.domain.BaseExceptionType;
import lombok.Getter;

public class UserException extends RuntimeException{
    @Getter
    private BaseExceptionType exceptionType;

    public UserException(BaseExceptionType exceptionType){
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
