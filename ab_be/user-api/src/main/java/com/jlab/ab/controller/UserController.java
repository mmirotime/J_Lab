package com.jlab.ab.controller;

import com.jlab.ab.domain.User;
import com.jlab.ab.dto.request.JoinForm;
import com.jlab.ab.dto.request.UserUpdateForm;
import com.jlab.ab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController { //http://localhost:8801/users/{id}?page=1&파라미터값
    @Autowired
    public UserService userService;

    //user Create
    @PostMapping
    public ResponseEntity<Long> join(@RequestBody JoinForm joinForm){
        Long userId = userService.createUser(joinForm);

        return ResponseEntity.ok(userId);
    }

    //user 정보 하나 불러오기
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.getUser(id);

        return ResponseEntity.ok(user);
    }

    //user 리스트 불러오기
    @GetMapping
    public List<User> getUserList(){
        List<User> userList = userService.getUserList();

        return userList;
    }

    //user 정보 수정하기
    @PutMapping("/{id}") //PathVariable 할때는 "/{변수}"
    public ResponseEntity<Long> updateUser(@PathVariable Long id, @RequestBody UserUpdateForm userUpdateForm){
        Long userId = userService.updateUser(id, userUpdateForm);

        return ResponseEntity.ok(userId);
    }

    //user 정보 삭제하기.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        String msg = userService.deleteUser(id);
        return ResponseEntity.ok(msg);
    }
}
