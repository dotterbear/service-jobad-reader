package com.dotterbear.jobad.reader.data.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dotterbear.jobad.reader.data.model.JobAd;
import com.dotterbear.jobad.reader.data.repo.JobAdRepository;

@Service
public class JobAdService {
	
	private static final Logger log = LoggerFactory.getLogger(JobAdService.class);
	
	@Autowired
	private JobAdRepository jobAdRepository;

	public JobAd findById(String id) {
		log.debug("findById, id: {}", id);
		return jobAdRepository.findById(id).get();
	}

	public List<JobAd> findAllOrderByTs(int page, int size) {
		log.debug("findAllOrderByTs, page: {}, size: {}", page, size);
		List<JobAd> jobAds = jobAdRepository.findAll(PageRequest.of(page - 1, size)).getContent();
		return jobAds;
	}

}
