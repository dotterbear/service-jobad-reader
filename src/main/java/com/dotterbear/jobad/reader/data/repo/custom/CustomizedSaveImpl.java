package com.dotterbear.jobad.reader.data.repo.custom;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.dotterbear.jobad.reader.data.model.JobAd;

public class CustomizedSaveImpl implements CustomizedSave {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public JobAd save(JobAd entity) {
		entity.setTs(new Date());
		mongoTemplate.save(entity);
		return entity;
	}

}
