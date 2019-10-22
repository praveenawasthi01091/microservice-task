package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication  /* config, enableautoconfig, componentscann*/
@EnableEurekaClient
public class TrackServiceApplication {

	public static void main(String[] args) {

	    SpringApplication.run(TrackServiceApplication.class, args);
	}

}
