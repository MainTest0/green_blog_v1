package com.tenco.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenco.blog.model.User;

// @Repository // 여기서는 생략 가능 (이유: JpaRepository에 이미 다 선언 되어있음)
// T: Table | ID: table의 pk
public interface UserRepository extends JpaRepository<User, Integer>{
	// 코드를 여기까지 쓰면 해주는 기능
	// select, selectAll, insert, update, delete 등
}
