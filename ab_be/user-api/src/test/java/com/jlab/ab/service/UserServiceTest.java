package com.jlab.ab.service;

import com.jlab.ab.domain.User;
import com.jlab.ab.domain.UserSex;
import com.jlab.ab.dto.request.JoinForm;
import com.jlab.ab.dto.request.UserUpdateForm;
import com.jlab.ab.dto.response.UserList;
import com.jlab.ab.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private Long givenId;

    @BeforeEach
    void setData() {
        MockitoAnnotations.openMocks(this);

        givenId = 1004L;

        user = User.builder()
                .email("test@gmail.com")
                .pwd("1111")
                .phoneNum("010-1234-5678")
                .address("서울특별시 강남구 테헤란로 100")
                .sex(UserSex.MALE)
                .name("테스터")
                .build();

        ReflectionTestUtils.setField(user, "id", givenId);
    }

    @DisplayName("신규 회원 가입")
    @Test
    void createUser() {
        // given
        JoinForm joinForm = new JoinForm();
        joinForm.setEmail(user.getEmail());
        joinForm.setPwd(user.getPwd());

        User newUser = joinForm.toEntity(); // Mock 객체인 userRepository를 통해 회원 등록 시 돌려줄 값

        Long expectedId = 10L;
        ReflectionTestUtils.setField(newUser, "id", expectedId);    // 테스트를 위해 id값 주입

        given(userRepository.save(any(User.class))).willReturn(newUser);

        // when
        Long userId = userService.createUser(joinForm);

        // then
        assertEquals(expectedId, userId);
    }
    
    @DisplayName("단일 회원 조회")
    @Test
    void getUserInfo() {
        // given
        Long givenId = 1004L;

        given(userRepository.findById(givenId)).willReturn(Optional.of(user));

        // when
        User response = userService.getUser(givenId);

        // then
        assertAll(
                () -> assertEquals(response.getEmail(), user.getEmail()),
                () -> assertEquals(response.getPhoneNum(), user.getPhoneNum()),
                () -> assertEquals(response.getName(), user.getName()),
                () -> assertEquals(response.getAddress(), user.getAddress()),
                () -> assertEquals(response.getSex(), user.getSex())
        );
    }

    @DisplayName("전체 회원 목록 조회")
    @Test
    void getWholeUserList() {
        // given
        List<User> userList = new ArrayList<>();
        userList.add(user);

        given(userRepository.findAll()).willReturn(userList);

        // when
        List<User> response = userService.getUserList();

        // then
        assertAll(
                () -> assertEquals(userList.size(), response.size()),
                () -> assertEquals(response.get(0).getId(), givenId)
        );
    }

    @DisplayName("회원 정보 수정")
    @Test
    void updateUser() {
        // given
        String newEmail = "new@gmail.com";
        String newPwd = "new9999";
        String newName = "새테스터";
        String newPhoneNum = "010-9999-9999";
        String newAddress = "서울특별시 새 주소";
        UserSex newSex = UserSex.NONE;

        UserUpdateForm updateForm = new UserUpdateForm();
        updateForm.setEmail(newEmail);
        updateForm.setPwd(newPwd);
        updateForm.setName(newName);
        updateForm.setPhoneNum(newPhoneNum);
        updateForm.setAddress(newAddress);
        updateForm.setSex(newSex);

        Long expectedId = 1001L;
        ReflectionTestUtils.setField(user, "id", expectedId);

        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));

        // when
        Long userId = userService.updateUser(expectedId, updateForm);

        // then
        assertAll(
                () -> assertEquals(expectedId, userId),
                () -> assertEquals(newEmail, user.getEmail()),
                () -> assertEquals(newPwd, user.getPwd()),
                () -> assertEquals(newName, user.getName()),
                () -> assertEquals(newPhoneNum, user.getPhoneNum()),
                () -> assertEquals(newAddress, user.getAddress()),
                () -> assertEquals(newSex, user.getSex())
        );
    }

    @DisplayName("회원 삭제")
    @Test
    void deleteUser() {
        // given
        Long givenUserId = 1001L;
        ReflectionTestUtils.setField(user, "id", givenUserId);

        given(userRepository.findById(givenUserId)).willReturn(Optional.of(user));

        // when
        String msg = userService.deleteUser(givenUserId);

        // then
        verify(userRepository).findById(givenUserId);
        verify(userRepository).delete(any(User.class));
        assertEquals(givenUserId + " 삭제에 성공하였습니다.", msg);
    }

}