package com.jlab.ab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    //400
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> ItemNotFound(ItemNotFoundException e){
        String msg = "찾는 상품이 없습니다.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

    //501
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<String> globalException(GlobalException e){
        String msg = "서버오류";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
    }


    @ExceptionHandler(MethodInvalidException.class)
    public ResponseEntity<String> MethodInvalid(MethodInvalidException e){
        String msg = "빈칸을 입력해주세요";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

}
