package com.jlab.ab.dto.request;

import com.jlab.ab.domain.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class JoinForm {
    private Long u_id;

    private String email;

    private String pwd;

    private String u_name;

    private String phone_num;

    private String user_address;

    private char sex;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .user_address(user_address)
                .email(email)
                .pwd(pwd)
                .sex(sex)
                .phone_num(phone_num)
                .u_name(u_name)
                .build();
    }
}
