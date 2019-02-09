package com.dotterbear.jobad.reader.data.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dotterbear.jobad.reader.data.model.JobAd;
import com.dotterbear.jobad.reader.data.model.WebSiteEnum;
import com.dotterbear.jobad.reader.data.repo.custom.CustomizedSave;

@Repository
public interface JobAdRepository extends MongoRepository<JobAd, String>, CustomizedSave {

	public List<JobAd> findByFromWebSiteAndExtRefId(WebSiteEnum fromWebSite, String extRefId);

	public Page<JobAd> findAllByOrderByTs(PageRequest pageRequest);
}
