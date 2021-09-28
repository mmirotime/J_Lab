package com.jlab.ab.service;

import com.jlab.ab.domain.UserEntity;
import com.jlab.ab.domain.UserRepository;
import com.jlab.ab.dto.request.JoinForm;
import com.jlab.ab.dto.request.UpdateForm;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public Long createUser(JoinForm joinForm) {
        UserEntity request = joinForm.toEntity();

        UserEntity newUser = userRepository.save(request);

        return newUser.getU_id();
    }

    public UserEntity getUser(Long u_id) {
        UserEntity user = userRepository.findById(u_id)
                .orElseThrow(()->new IllegalArgumentException("찾는 유저가 없습니다."));

        return user;
    }

    public List<UserEntity> getUserList() {
        List<UserEntity> userList = userRepository.findAll();
        return userList;
    }

    public Long updateUser(Long u_id, UpdateForm updateForm) {
        UserEntity user = userRepository.findById(u_id)
                .orElseThrow(()->new IllegalArgumentException("찾는 유저가 없습니다."));
        user.update(updateForm.getPwd(),updateForm.getU_name(),
                updateForm.getPhone_num(),updateForm.getUser_address(),updateForm.getSex());
        return user.getU_id();
    }

    public String deleteUser(Long u_id) {
        String msg = u_id+" 삭제에 성공하였습니다.";
        UserEntity user = userRepository.findById(u_id)
                .orElseThrow(()->new IllegalArgumentException("찾는 유저가 없습니다."));
        try {
            userRepository.delete(user);
        }catch(Exception e){
            msg = "삭제를 할 수 없습니다.";
        }

        return msg;

    }
}
