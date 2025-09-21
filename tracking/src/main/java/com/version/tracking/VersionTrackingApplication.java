package com.version.tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VersionTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VersionTrackingApplication.class, args);
	}

}
