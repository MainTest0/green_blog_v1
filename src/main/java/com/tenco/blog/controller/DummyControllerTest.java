package com.tenco.blog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tenco.blog.model.User;
import com.tenco.blog.repository.UserRepository;

// 데이터를 내려주는 restController
@RestController // Ioc 처리 (Inversion of controller) 제어의 역전
// repositoryTest
public class DummyControllerTest {

	// DI
	// @Autowired
	private UserRepository userRepository;

	// Autowired 순수코드로 쓰기
	public DummyControllerTest(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// MIME TYPE - JSON형식 ( application/json )
	// 회원등록 - 샘플
	@PostMapping("/dummy/insertUser")
	public String insertUser(@Validated @RequestBody User user) {

		// 유효성 검사 생략
		user.setRole("user"); // role에 값 넣기
		userRepository.save(user);
		System.out.println(user.toString());
		System.out.println("여기코드 동작하나요?");

		return "회원가입에 성공";
	}

	// localhost:8080/dummy/user/1
	@GetMapping("/dummy/user/{id}")
	public User getUser(@PathVariable Integer id) {

		// optional - user가 있을 수도 있고 null일 수도 있다.

		// 인증검사, 유효성 검사 생략
		// Optional<User> userOp = userRepository.findById(id);
		// 1.
		// User user = userRepository.findById(id).get();

		// 2.
//		User user = userRepository.findById(id).orElseGet(() -> {
//			return new User();
//		});
		// 3.
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없네요");
		});

		// 정리
		// 1. get(): user객체가 null일 경우가 없을 때 사용
		// 2. orElseGet(): 데이터가 있으면 그대로 반환,
		// 없으면 직접 정의한 객체를 반환 시킬 수 있다.
		// 3. orElseThrow: 있으면 반환, 없으면 예외 던진다.

		System.out.println(user);

		return user;

	}
	
	@GetMapping("/dummy/users")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC)
	Pageable pageable){
		//List<User> users = userRepository.findAll();
		
		Page<User> pageUser = userRepository.findAll(pageable);
		
		
		return pageUser.getContent();
	}
	
	@GetMapping("/dummy/test")
	public List<User> pageTest(@PageableDefault(size = 1, sort = "username", value = 3, direction = Direction.ASC)
	Pageable pageable){
		
		Page<User> pageTest = userRepository.findAll(pageable);
		
		return pageTest.getContent();
	}
	
	// JSON으로 데이터 던질 예정
	// Update를 할 때 1번 방식은 기존 로직 처리했고,
	// 이번에 배울거 dirty checking 사용해보기
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@Validated @PathVariable Integer id, @RequestBody User reqUser) {
		
		// 인증검사, 유효성 검사
		// id 존재 여부 확인
		// 영속화된 데이터
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저가 존재하지 않습니다.");
		});
		
		// 클라이언트가 던진 데이터
		// reqUser
		userEntity.setEmail(reqUser.getEmail());
		userEntity.setPassword(reqUser.getPassword());
		
		// 저장 처리
		// userRepository.save(userEntity);
		
		// dirty checking 사용
		// save를 호출하지 않고 변경처리 되었다.
		// 이유 : 트랜잭션 내에서 트랜잭션이 끝나기 전에 영속성 context의
		// 1차 캐시에 들어가있는 데이터 상태를 변경 감지한다.
		
		
		return userEntity;
	}
	
	@DeleteMapping("/dummy/delete/{id}")
	public void deleteUser(@PathVariable Integer id) {
		
		userRepository.deleteById(id);
		System.out.println("삭제");
		
	}
	
}
