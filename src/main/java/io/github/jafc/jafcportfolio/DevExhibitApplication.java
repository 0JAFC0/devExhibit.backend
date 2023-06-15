package io.github.jafc.jafcportfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DevExhibitApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevExhibitApplication.class, args);
	}
}
