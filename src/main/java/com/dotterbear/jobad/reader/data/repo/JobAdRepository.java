package com.dotterbear.jobad.reader.data.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dotterbear.jobad.reader.data.model.JobAd;
import com.dotterbear.jobad.reader.data.model.WebSiteEnum;
import com.dotterbear.jobad.reader.data.repo.custom.CustomizedSave;

public interface JobAdRepository extends MongoRepository<JobAd, String>, CustomizedSave {

	public List<JobAd> findByFromWebSiteAndExtRefId(WebSiteEnum fromWebSite, String extRefId);
}
