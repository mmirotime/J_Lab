package com.jlab.ab.dto.request;

import com.jlab.ab.domain.User;
import com.jlab.ab.domain.UserSex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserUpdateForm {
    private Long id;

    private String email;

    private String pwd;

    private String name;

    private String phoneNum;

    private String address;

    private UserSex sex;

    public User toEntity(){
        return User.builder()
                .address(address)
                .pwd(pwd)
                .sex(sex)
                .phoneNum(phoneNum)
                .name(name)
                .build();
    }
}
