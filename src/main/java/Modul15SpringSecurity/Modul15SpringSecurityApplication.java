package Modul15SpringSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Modul15SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Modul15SpringSecurityApplication.class, args);
		//System.out.println(new BCryptPasswordEncoder().encode("super_secret_password"));
	}

}
