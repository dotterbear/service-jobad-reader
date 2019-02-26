package com.dotterbear.jobad.reader.data.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	public Page<JobAd> findAllOrderByTs(int page, int size) {
		log.debug("findAllOrderByTs, page: {}, size: {}", page, size);
		return jobAdRepository.findAll(PageRequest.of(page - 1, size));
	}

}
