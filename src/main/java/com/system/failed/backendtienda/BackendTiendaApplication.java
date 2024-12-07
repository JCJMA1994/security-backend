package com.system.failed.backendtienda;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendTiendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendTiendaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder) {
		return args ->
		{
			System.out.println("Password: " + passwordEncoder.encode("password1"));
			System.out.println("Password: " + passwordEncoder.encode("password2"));
			System.out.println("Password: " + passwordEncoder.encode("password3"));
		};
	}


}
