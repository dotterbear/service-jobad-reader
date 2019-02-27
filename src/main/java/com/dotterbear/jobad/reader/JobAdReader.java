package com.dotterbear.jobad.reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableFeignClients(basePackages = "com.dotterbear.jobad.feign.client")
public class JobAdReader {

	public static void main(String[] args) {
		SpringApplication.run(JobAdReader.class, args);
	}

}
