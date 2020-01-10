# spring-boot-security-oauth2


## 프로젝트 이력

https://github.com/aldwindelgado/spring-boot-oauth2-server
프로젝트를 fork함.

### [Spring Boot 2로 변경]
https://github.com/woozoo73/spring-boot-oauth2-server/commit/514efb05357bfaf425ad5eaa3dd61262d82bb15b

#### 1. pom.xml 파일 변경
...

### [사용자 정보 자동 입력]
#### 1. 서버 기동시 사용자 정보를 추가하도록 수정함
...


## 사용법
### 1. MySQL 서버 실행
```
> docker-compse -f docker-compose-mysql.yml up -d
```
### 2. MySQL 서버 확인(Optional)
```
> docker ps
> docker exec -it spring-boot-oauth2-server_mysql_1 bash
```
### 3. OAuth2-Server(Authorization Server) 애플리케이션 실행 - 포트: 8080
```
> mvn clean package spring-boot:run
```
  아래와 같이 서버 기동시 사용자 정보가 추가됩니다.

```java
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
```

### 4. OAuth2-Client(Resource Server) 애플리케이션 실행 - 포트: 10080
https://github.com/woozoo73/spring-boot-oauth2-client
```
> mvn clean package spring-boot:run
```

### 5. Postman을 통한 API 확인

#### 1. token 요청(Authorization 설정)
![postman-01.png](https://github.com/woozoo73/spring-boot-oauth2-server/blob/master/postman-01.png "postman-01")

#### 2. token 요청(Body 설정)
![postman-02.png](https://github.com/woozoo73/spring-boot-oauth2-server/blob/master/postman-02.png "postman-02")

#### 3. token 요청(결과)
![postman-03.png](https://github.com/woozoo73/spring-boot-oauth2-server/blob/master/postman-03.png "postman-03")

#### 4. token을 통한 자원 접근
![postman-04.png](https://github.com/woozoo73/spring-boot-oauth2-server/blob/master/postman-04.png "postman-04")
