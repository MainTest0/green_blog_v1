package com.tenco.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tenco.blog.model.User;

// @Repository // 여기서는 생략 가능 (이유: JpaRepository에 이미 다 선언 되어있음)
// T: Table | ID: table의 pk
public interface UserRepository extends JpaRepository<User, Integer> {
	// 코드를 여기까지 쓰면 해주는 기능
	// select, selectAll, insert, update, delete 등

	// Spring JPA 네이밍 전략
	// 메서드 이름으로 JPA가 쿼리를 만들어 준다.
	// ( 규칙을 지킨다면 )
	// SELECT * FROM user WHERE username = ? AND password = ?;
	User findByUsernameAndPassword(String username, String password);

	// 두번째 방법
	@Query(value = " SELECT * "
			+ " FROM user WHERE username = ?1 "
			+ " AND password = ?2 ", nativeQuery = true)
	User login(String username, String password);
	
	// 쿼리 만들어 주세요
	// select * from user where username = ?;
	Optional<User> findByUsername(String username);
	// Optional 쓰면 getOrElseThrows 쓰는기능 해줌
	
}
