package com.SpringSecurity.SpringSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
public class SpringSecurityApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run(UserService userService){
//		return args -> {
//			userService.saveRole(new Role(null,"ROLE_USER"));
//			userService.saveRole(new Role(null,"ROLE_MANAGER"));
//			userService.saveRole(new Role(null,"ROLE_ADMIN"));
//			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
//
//
//			userService.saveUser(new User(null,"nguyen van a","a","phamhuuthien@gmail.com","12345", new HashSet<>()));
//			userService.saveUser(new User(null,"nguyen van b","b","phamhuuthien1@gmail.com","12345", new HashSet<>()));
//
//
//
//			userService.addToUser("phamhuuthien@gmail.com","ROLE_USER");
//			userService.addToUser("phamhuuthien@gmail.com","ROLE_MANAGER");
//
//
//			userService.addToUser("phamhuuthien1@gmail.com","ROLE_ADMIN");
//			userService.addToUser("phamhuuthien1@gmail.com","ROLE_SUPER_ADMIN");
//
//
//		};
//	}
}
