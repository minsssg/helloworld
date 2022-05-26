package helloworld.test.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

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
		System.out.println(user);
		assertThat(user.getName()).isEqualTo("김민석");
	}
}
