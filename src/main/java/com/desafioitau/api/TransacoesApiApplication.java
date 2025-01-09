package com.desafioitau.api;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TransacoesApiApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TransacoesApiApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

}
