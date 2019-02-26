package com.dotterbear.jobad.reader.data.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dotterbear.jobad.reader.data.model.JobAd;
import com.dotterbear.jobad.reader.data.repo.custom.CustomizedSave;

@Repository
public interface JobAdRepository extends MongoRepository<JobAd, String>, CustomizedSave {
}
