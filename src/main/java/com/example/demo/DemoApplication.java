package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {

	Commands commands =	SpringApplication.run(DemoApplication.class, args).getBean(Commands.class);
	commands.run();

	}

}
