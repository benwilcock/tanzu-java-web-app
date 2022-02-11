package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

	private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

	@Value("${spring.application.name:tanzu-java-web-app}")
    String appName;

	@RequestMapping("/")
	public Map<String, String> index() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

		LOG.info("A request has been received for the /rest endpoint.");
		Map<String, String> data = new HashMap<String, String>();
		data.put("Application Name:", appName);
		data.put("Greetings From:", "Ben, VMware, Spring Boot, and Tanzu!");
		data.put("The Date & Time:", dtf.format(now));
		LOG.debug("Returning {}.", data.toString());
		return data;
	}
}