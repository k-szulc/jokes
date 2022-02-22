package xyz.itbs.jokes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JokesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JokesApplication.class, args);
	}

}
