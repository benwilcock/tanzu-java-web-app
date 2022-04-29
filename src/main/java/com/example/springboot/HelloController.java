package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

	private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

	@Value("${spring.application.name:tanzu-java-web-app}")
    String appName;

	@Value("${message:Tanzu Application Platform}")
	String message;

	@Value("${configfrom:Code}")
	String configfrom;

	@Value("${timezoneid:America/Los_Angeles}")
	String timezoneid;
	

	@RequestMapping("/")
	public Map<String, String> index() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        ZonedDateTime now = Instant.now().atZone(ZoneId.of(timezoneid));

		LOG.info("A request has been received for the / endpoint.");
		Map<String, String> data = new HashMap<String, String>();
		data.put("Application Name", appName);
		data.put("Greetings from Webinar", message);
		data.put("Config from", configfrom);
		data.put("Date & Time", dtf.format(now));
		data.put("Timezone", timezoneid);
		LOG.debug("Returning {}.", data.toString());
		return data;
	}
}