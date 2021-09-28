package com.jlab.ab.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Table(name = "UserEntity", indexes = {
        @Index(name = "idx_userentity_u_name", columnList = "u_name")
})
@Entity
@Getter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long u_id;

    @Column(nullable = false)
    //email을 id로 이용함.
    private String email;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private String u_name;


    private String phone_num;


    private String user_address;

    private char sex;

    @Builder
    public UserEntity(Long u_id, String email, String pwd, String u_name, String phone_num, String user_address, char sex) {
        this.u_id = u_id;
        this.email = email;
        this.pwd = pwd;
        this.u_name = u_name;
        this.phone_num = phone_num;
        this.user_address = user_address;
        this.sex = sex;
    }

    public void update(String pwd, String u_name, String phone_num, String user_address, char sex) {
        this.pwd = pwd;
        this.u_name = u_name;
        this.phone_num = phone_num;
        this.user_address = user_address;
        this.sex = sex;
    }
}
