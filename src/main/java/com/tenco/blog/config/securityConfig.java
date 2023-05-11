package com.tenco.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tenco.blog.auth.PrincipalDetail;
import com.tenco.blog.auth.PrincipalDetailService;

/**
 * 
 * 권한부여 페이지 설정
 * /auth/joinPage
 * /authloginPage
 * /auth/**
 */

@Configuration
@EnableWebSecurity	// 시큐리티 필터로 등록을 해라 ( 필터 커스텀 )
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증처리를 미리 하겠다.
public class securityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	
	// 특정 주소 필터를 설정할 예정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable(); // csrf 토큰 비활성화 처리(테스트시 사용 권장)
		
		http.authorizeHttpRequests()
		.antMatchers("/auth/**","/","/js/**","/images/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/auth/loginPage")	// <- 시큐리티 로그인 페이지를 우리가 만든 페이지로 커스텀
		.loginProcessingUrl("/auth/loginProc")
		.defaultSuccessUrl("/");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePwd());
		
	}
	
}
