package com.dotterbear.jobad.reader.rest.service;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dotterbear.jobad.reader.data.model.JobAd;
import com.dotterbear.jobad.reader.data.service.JobAdService;
import com.dotterbear.jobad.rest.model.JobAdDetailResponse;
import com.dotterbear.jobad.rest.model.JobAdItem;
import com.dotterbear.jobad.rest.model.JobAdListResponse;

@Service
public class JobAdApiService {

	private static final Logger log = LoggerFactory.getLogger(JobAdApiService.class);

	@Autowired
	private JobAdService jobAdService;

	public ResponseEntity<JobAdDetailResponse> findJobAdById(String id) {
		log.debug("findByJobAdId, id: {}", id);
		JobAdItem jobAdItem = buildJobAdItem(jobAdService.findById(id));
		JobAdDetailResponse jobAdDetailResponse = new JobAdDetailResponse()
				.jobAdItem(jobAdItem);
		return new ResponseEntity<JobAdDetailResponse>(jobAdDetailResponse, HttpStatus.OK);
	}

	public ResponseEntity<JobAdListResponse> findJobAds(int size, int page) {
		log.debug("findJobAds, size: {}, page: {}");
		Page<JobAd> jobAds = jobAdService.findAllOrderByTs(page, size);
		List<JobAdItem> jobAdItems = jobAds.getContent().stream()
			.map(JobAdApiService::buildJobAdItem)
			.collect(Collectors.toList());
		JobAdListResponse jobAdList = new JobAdListResponse()
				.totalPageNum(jobAds.getTotalPages())
				.totalItemNum(jobAds.getTotalElements())
				.jobAdItems(jobAdItems);
		return new ResponseEntity<JobAdListResponse>(jobAdList, HttpStatus.OK);
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
