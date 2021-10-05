package com.jlab.ab.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table( indexes = { @Index(name = "idx_user_u_name", columnList = "u_name") })
@Entity
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    //Java는 카멜케이스

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID") //네츄럴 조인. 외래키 관련.
    //Id 명명 할 때,
    private Long id;

    @Column(nullable = false)
    //email을 id로 이용함.
    private String email;

    @Column(nullable = false)
    @Setter
    private String pwd;

    @Column(nullable = false)
    @Setter
    private String name;

    @Setter
    private String phoneNum;

    @Setter
    private String address; //address 객체를 만들어 관리할 것인지.

    @Enumerated(EnumType.STRING)
    @Setter
    private UserSex sex; //기본적으로 저장되는 값은 순서에 따른 순서값으로 저장되어짐. > @Enumrated 로 저장값 설정

    @Builder
    public User(Long id, String email, String pwd, String name, String phoneNum, String address, UserSex sex) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
        this.sex = sex;
    }

//    아래와 같이 메서드로 이용할 때는 변수는 4개 이하로 할 것.
//    public void update(String pwd, String u_name, String phone_num, String user_address, UserSex sex) {
//        this.pwd = pwd;
//        this.name = u_name;
//        this.phoneNum = phone_num;
//        this.address = user_address;
//        this.sex = sex;
//    }


}
