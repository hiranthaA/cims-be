package com.example.cims;

import com.example.cims.model.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class CimsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CimsApplication.class, args);
	}

}
