package com.jlab.ab.service;

import com.jlab.ab.domain.User;
import com.jlab.ab.dto.response.UserList;
import com.jlab.ab.repository.UserRepository;
import com.jlab.ab.dto.request.JoinForm;
import com.jlab.ab.dto.request.UserUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Transactional //메서드 안에 실행 순서를 보장해줌. > 순서가 엉켜서 생기는 문제 방지. / 세이브도 진행해줌. (스프링프레임워크!!!)
    public Long createUser(JoinForm joinForm) {
        User request = joinForm.toEntity();
        User newUser = userRepository.save(request);

        return newUser.getId();
    }

    @Transactional(readOnly = true)
    public UserList getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("찾는 유저가 없습니다."));
        UserList userList = new UserList();

        userList.builder()
                .address(user.getAddress())
                .email(user.getEmail())
                .name(user.getName())
                .phoneNum(user.getPhoneNum())
                .id(user.getId())
                .build();
        return userList;
    }

    @Transactional(readOnly = true) // 조회성능 향상.
    public List<User> getUserList() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Transactional
    public Long updateUser(Long id, UserUpdateForm userUpdateForm) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("찾는 유저가 없습니다."));
        user.setAddress(userUpdateForm.getAddress());
        user.setName(userUpdateForm.getName());
        user.setPhoneNum(userUpdateForm.getPhoneNum());
        user.setSex(userUpdateForm.getSex());
        user.setPwd(userUpdateForm.getPwd());
        return user.getId();
    }

    @Transactional
    public String deleteUser(Long id) {
        String msg = id+" 삭제에 성공하였습니다.";
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("찾는 유저가 없습니다."));
        try {
            userRepository.delete(user);
        }catch(Exception e){
            msg = "삭제를 할 수 없습니다.";
        }

        return msg;

    }
}
