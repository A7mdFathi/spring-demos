package com.ahmed.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.ahmed.bookstore.authentication.AuthenticationService;
import com.ahmed.bookstore.authentication.RegisterRequest;
import com.ahmed.bookstore.user.Role;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class BookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService service) {
		return args -> {
			var admin = RegisterRequest.builder().firstName("Ahmed")
					.lastName("Fathi")
					.email("a.fathi@example.com").password("test123")
					.role(Role.ADMIN)
					.build();
			System.out.println("Admin Token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder().firstName("yossef")
					.lastName("Fathi")
					.email("y.fathi@example.com").password("test123")
					.role(Role.MANAGER)
					.build();
			System.out.println("Manager Token: " + service.register(manager).getAccessToken());

		};
	}

}
