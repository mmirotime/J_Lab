package com.jlab.ab.dto.response;

import com.jlab.ab.domain.User;
import com.jlab.ab.domain.UserSex;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserList {
    private Long id;

    private String email;

    private String name;

    private String phoneNum;

    private String address;

    private UserSex sex;

    @Builder
    public UserList(Long id, String email, String name, String phoneNum, String address, UserSex sex) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
        this.sex = sex;
    }
}
