package br.com.juniorcorp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"br.com.juniorcorp.controller"})
public class Configuration {
	public static void main(String[] args) {
		SpringApplication.run(Configuration.class, args);
	}
}
