package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		StringBuffer buf = new StringBuffer();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

		buf.append("Greetings from Ben, VMware, Spring Boot, and Tanzu!");
		buf.append("<br>");
		buf.append("The time is: " + dtf.format(now));
		return buf.toString();
	}
}