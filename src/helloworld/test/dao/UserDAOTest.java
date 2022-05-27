package helloworld.test.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import helloworld.dao.UserDAO;
import helloworld.model.User;

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
	void loginSuccessTest() {
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
}
