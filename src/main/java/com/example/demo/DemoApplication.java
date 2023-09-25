package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class DemoApplication {
	@Bean
	public ApplicationRunner applicationStartupRunner() {
		return new ApplicationRunner();
	}

	public static void main(String[] args) {


		SpringApplication.run(DemoApplication.class, args);
		Commands.Run();
		Library library = new Library("1","123",true);

	}

}
