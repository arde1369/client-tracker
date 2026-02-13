package com.astroitsolutions.clienttracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.astroitsolutions.clienttracker", "com.astroitsolutions.data_services"} )
@EntityScan(basePackages = {"com.astroitsolutions.clienttracker", "com.astroitsolutions.data_services"})
public class ClientTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientTrackerApplication.class, args);
	}
}
