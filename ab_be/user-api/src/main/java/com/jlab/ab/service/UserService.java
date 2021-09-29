package com.jlab.ab.service;

import com.jlab.ab.domain.User;
import com.jlab.ab.repository.UserRepository;
import com.jlab.ab.dto.request.JoinForm;
import com.jlab.ab.dto.request.UpdateForm;
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
    public User getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("찾는 유저가 없습니다."));

        return user;
    }

    @Transactional(readOnly = true) // 조회성능 향상.
    public List<User> getUserList() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Transactional
    public Long updateUser(Long id, UpdateForm updateForm) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("찾는 유저가 없습니다."));
        user.setAddress(updateForm.getAddress());
        user.setName(updateForm.getName());
        user.setPhoneNum(updateForm.getPhoneNum());
        user.setSex(updateForm.getSex());
        user.setPwd(updateForm.getPwd());
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
