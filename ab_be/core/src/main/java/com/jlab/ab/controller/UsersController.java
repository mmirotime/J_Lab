package com.jlab.ab.controller;

import com.jlab.ab.domain.UserEntity;
import com.jlab.ab.dto.request.JoinForm;
import com.jlab.ab.dto.request.UpdateForm;
import com.jlab.ab.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    public UserService userService;

    //user Create
    @PostMapping
    public ResponseEntity<Long> join(@RequestBody JoinForm joinForm){
        Long userId = userService.createUser(joinForm);

        return ResponseEntity.ok(userId);
    }

    //user 정보 하나 불러오기
    @GetMapping("/{u_id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long u_id){
        UserEntity user = userService.getUser(u_id);

        return ResponseEntity.ok(user);
    }
    //user 리스트 불러오기
    @GetMapping
    public List<UserEntity> getUserList(){
        List<UserEntity> userList = userService.getUserList();

        return userList;
    }

    //user 정보 수정하기
    @PutMapping("{u_id}")
    public ResponseEntity<Long> updateUser(@PathVariable Long u_id, @RequestBody UpdateForm updateForm){
        Long userId = userService.updateUser(u_id, updateForm);

        return ResponseEntity.ok(userId);
    }

    //user 정보 삭제하기.
    @DeleteMapping("{u_id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long u_id){
        String msg = userService.deleteUser(u_id);
        return ResponseEntity.ok(msg);
    }
}
