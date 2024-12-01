package com.learn.springboot.pos_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PosProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosProjectApplication.class, args);
	}

}
