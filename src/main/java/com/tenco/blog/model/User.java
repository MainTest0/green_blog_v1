package com.tenco.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

// 도메인 객체 ( 테이블을 만들어주는 클래스 )
// 작은 프로젝트에서는 도메인 객체와 DTO 같이 써도 됨
@Entity
@Data // 주의 || (Object Mapper가 파싱) 파싱 처리할 때 setter 반드시 있어야 한다.
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, length = 30)
	private String username;
	
	@NotBlank // null, ""(빈문자열) 검사
	@Column(nullable = false, length = 100)
	private String password;
	
	@NotBlank
	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'")
	private  String role; // user, admin, manager
	
	@CreationTimestamp	// 자동 시간 입력 == now()
	private Timestamp createdDate;
}
