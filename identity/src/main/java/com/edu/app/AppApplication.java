package com.edu.app;

import com.edu.app.dto.request.UserCreateRequest;
import com.edu.app.repository.UserRepository;
import com.edu.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {
	@Autowired UserService userService;
	@Autowired
	UserRepository userRepository;

	public void  CrateAdmin(){
		if(userRepository.findByUsername("admin").isEmpty()){
			UserCreateRequest userCreateRequest= UserCreateRequest.builder()
					.username("admin")
					.password("admin22012003")
					.build();
			userService.crateNewAdmin(userCreateRequest);
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	public void run(String... args) {
		CrateAdmin(); // Gọi  khi ứng dụng đã khởi động
	}

}
