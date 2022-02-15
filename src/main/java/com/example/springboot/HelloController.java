package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

	private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private DiscoveryClient discoveryClient;

	@Value("${spring.application.name:tanzu-java-web-app}")
    String appName;

	@Value("${message:Tanzu Application Platform}")
	String message;

	@Value("${timezoneid:America/Los_Angeles}")
	String timezoneid;
	

	@RequestMapping("/")
	public Map<String, String> index() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        ZonedDateTime now = Instant.now().atZone(ZoneId.of(timezoneid));

		List<ServiceInstance> instances = discoveryClient.getInstances("spring-boot-admin");

		LOG.info("A request has been received for the / endpoint.");
		Map<String, String> data = new HashMap<String, String>();
		data.put("This Application Name:", appName);
		data.put("Greetings! From:", message);
		data.put("Date & Time:", dtf.format(now));
		data.put("Timezone:", timezoneid);
		data.put("Discoverable services:", discoveryClient.getServices().toString());
		data.put("Spring Boot Admin K8s URI:", instances.get(0).getUri().toASCIIString());
		LOG.debug("Returning {}.", data.toString());
		return data;
	}
}