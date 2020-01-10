package com.github.oauth2.server;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.oauth2.server.UserRepository;
import com.github.oauth2.server.User;

@Service
public class UserInit {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userDao;

	@PostConstruct
	public void printEndocedPassword() {
		User alex = user(1L, "Alex123", "password", 3456, 33);
		User tom = user(2L, "Tom234", "password", 7823, 23);
		User adam = user(3L, "Adam", "password", 4234, 45);
		User hazin = user(4L, "hazin", "woo", 52, 9);

		userDao.save(alex);
		userDao.save(tom);
		userDao.save(adam);
		userDao.save(hazin);
	}

	private User user(long id, String username, String password, long salary, int age) {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setSalary(salary);
		user.setAge(age);

		return user;
	}

}
