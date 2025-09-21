package com.document.editing;

import com.document.editing.service.FileReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.nio.file.WatchService;

@SpringBootApplication
@EnableDiscoveryClient
public class DocumentTrackingApplication  implements CommandLineRunner {
	@Autowired
	FileReadingService fileReadingService;
	public static void main(String[] args) {
		SpringApplication.run(DocumentTrackingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
