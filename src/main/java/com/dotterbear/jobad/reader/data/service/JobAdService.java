package com.dotterbear.jobad.reader.data.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
		Optional<JobAd> jobAd = jobAdRepository.findById(id);
		return jobAd.isPresent() ? jobAd.get() : null;
	}

	public Page<JobAd> findAll(int page, int size, String direction, String propertie) {
		log.debug("findAllOrderByTs, page: {}, size: {}", page, size);
		return jobAdRepository.findAll(PageRequest.of(page - 1, size, Sort.by(direction, propertie)));
	}

}
