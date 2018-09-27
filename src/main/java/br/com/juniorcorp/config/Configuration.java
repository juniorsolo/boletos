package br.com.juniorcorp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages= {"br.com.juniorcorp.controller","br.com.juniorcorp.service"})
@EntityScan("br.com.juniorcorp.model")
@EnableJpaRepositories("br.com.juniorcorp.repository")
public class Configuration {
	public static void main(String[] args) {
		SpringApplication.run(Configuration.class, args);
	}
}
