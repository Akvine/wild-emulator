package ru.akvine.wild.emulator.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"ru.akvine.wild.emulator.core",
		"ru.akvine.wild.emulator.admin",
		"ru.akvine.wild.emulator.api"})
@EntityScan(basePackages = "ru.akvine.wild.emulator.core")
@EnableJpaRepositories(basePackages = "ru.akvine.wild.emulator.core")
public class WildEmulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WildEmulatorApplication.class, args);
	}

}
