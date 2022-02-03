package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
		return dtf.format(now) + "<br>Greetings from VMware, Spring Boot, and Tanzu! ";
	}
}