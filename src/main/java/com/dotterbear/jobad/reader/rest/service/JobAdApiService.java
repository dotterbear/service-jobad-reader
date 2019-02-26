package com.dotterbear.jobad.reader.rest.service;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dotterbear.jobad.reader.data.model.JobAd;
import com.dotterbear.jobad.reader.data.service.JobAdService;
import com.dotterbear.jobad.rest.model.JobAdItem;
import com.dotterbear.jobad.rest.model.JobAdList;

@Service
public class JobAdApiService {

	private static final Logger log = LoggerFactory.getLogger(JobAdApiService.class);

	@Autowired
	private JobAdService jobAdService;

	public ResponseEntity<JobAdItem> findJobAdById(String id) {
		log.debug("findByJobAdId, id: {}", id);
		JobAdItem jobAdItem = buildJobAdItem(jobAdService.findById(id));
		return new ResponseEntity<JobAdItem>(jobAdItem, HttpStatus.OK);
	}

	public ResponseEntity<JobAdList> findJobAds(int size, int page) {
		log.debug("findJobAds, size: {}, page: {}");
		List<JobAdItem> jobAdItems = jobAdService.findAllOrderByTs(page, size).stream()
			.map(JobAdApiService::buildJobAdItem)
			.collect(Collectors.toList());
		JobAdList jobAdList = new JobAdList()
				.jobAdItems(jobAdItems);
		return new ResponseEntity<JobAdList>(jobAdList, HttpStatus.OK);
	}

	private static JobAdItem buildJobAdItem(JobAd jobAd) {
		if (jobAd == null)
			return null;
		return new JobAdItem()
				.id(jobAd.getId())
				.companyName(jobAd.getCompanyName())
				.title(jobAd.getTitle())
				.industry(jobAd.getIndustry())
				.careerLevel(jobAd.getCareerLevel())
				.qualification(jobAd.getQualification())
				.location(jobAd.getLocation())
				.employmentType(jobAd.getEmploymentType())
				.others(jobAd.getOthers())
				.yearsOfExp(jobAd.getYearsOfExp())
				.benefits(jobAd.getBenefits().stream().collect(Collectors.toList()))
				.postedDate(jobAd.getPostedDate().toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate())
				.fromWebSite(jobAd.getFromWebSite().name())
				.url(jobAd.getUrl());
	}
}
