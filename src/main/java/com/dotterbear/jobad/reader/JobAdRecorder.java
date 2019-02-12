package com.dotterbear.jobad.reader;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.dotterbear.jobad.reader.data.model.JobAd;
import com.dotterbear.jobad.reader.data.repo.JobAdRepository;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.dotterbear.jobad.feign.client")
public class JobAdRecorder {

	@Autowired
	private JobAdRepository jobAdRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	private Predicate<JobAd> isNewJobAd = jobAd -> jobAd != null
			&& jobAdRepository.findByFromWebSiteAndExtRefId(jobAd.getFromWebSite(), jobAd.getExtRefId()).size() == 0;

	public static void main(String[] args) {
		SpringApplication.run(JobAdRecorder.class, args);
	}

}
