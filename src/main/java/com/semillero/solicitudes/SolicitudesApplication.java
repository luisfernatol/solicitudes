package com.semillero.solicitudes;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.*;
import java.security.SecurityPermission;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan("com.semillero.solicitudes.persistence")
@EnableJpaRepositories("com.semillero.solicitudes.persistence")
public class SolicitudesApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SolicitudesApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
