package com.gabriel.reservaareacomum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ReservaAreaComumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservaAreaComumApplication.class, args);
	}
}
