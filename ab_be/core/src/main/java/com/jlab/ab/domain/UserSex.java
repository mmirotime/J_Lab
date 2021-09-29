package com.jlab.ab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserSex {
    MALE(1,'M')
    ,FEMALE(2,'F')
    ,NONE(3,'N') //공개안함
    ;

    private Integer id;
    private Character code;
}
