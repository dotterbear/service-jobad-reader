package com.dotterbear.jobad.reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.dotterbear.jobad.feign.client")
public class JobAdRecorder {

	public static void main(String[] args) {
		SpringApplication.run(JobAdRecorder.class, args);
	}

}
