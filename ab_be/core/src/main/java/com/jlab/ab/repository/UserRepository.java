package com.jlab.ab.repository;

import com.jlab.ab.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Component/Repository/Service/RestController 내가 수정할 수 있는 직접만든 객체를 스프링 빈으로 등록
// @Bean 추적 못하는 외부 라이브러리 등록
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
