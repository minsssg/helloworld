package com.helloworld.test.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.helloworld.dao.UserDAO;
import com.helloworld.model.User;

public class UserDAOTest {

	UserDAO userDAO;
	
	@BeforeEach
	public void initUserDAO() {
		userDAO = new UserDAO();
	}
	
	@Test
	void findByIdTest() {
		User user = userDAO.findById("minseok");
		assertThat(user.getName()).isEqualTo("김민석");
	}
	
	@Test
	@DisplayName("로그인 성공 테스트케이스")
	void joinSuccessTest() {
		User user = new User("silverlight_me", "1234", "김은희", "여성", "01012341234");
		
		assertThat(userDAO.join(user)).isEqualTo(-1);
	}
	
	@Test
	@DisplayName("User 리스트 출력")
	void findAllTest() {
		List<User> userList = userDAO.findAll();
		
		for (User user : userList) {
			System.out.println(user);
		}
	}
	
	@Test
	@DisplayName("User 로그인 존재하지 않는 아이디 테스트")
	void loginFailureNotExistId() {
		
		int result = userDAO.login("minseok1234", "12341234");
		
		assertThat(result).isEqualTo(-1);
	}
	
	@Test
	@DisplayName("user 로그인 시 비밀번호 틀린 경우")
	void loginFailurePassword() {
		int result = userDAO.login("minseok", "123asdf41234");
		assertThat(result).isEqualTo(0);
	}
	
	@Test
	@DisplayName("user 로그인  성공 테스트")
	void loginSuccessTest() {
		int result = userDAO.login("minseok", "12341234");
		assertThat(result).isEqualTo(1);
	}
}