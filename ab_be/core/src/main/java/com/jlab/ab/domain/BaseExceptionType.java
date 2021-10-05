package com.jlab.ab.domain;

public interface BaseExceptionType {
    int getErrorCode();
    int getHttpStatus();
    String getMessage();
}