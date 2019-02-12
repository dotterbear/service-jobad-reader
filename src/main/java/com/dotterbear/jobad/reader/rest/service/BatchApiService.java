package com.dotterbear.jobad.reader.rest.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dotterbear.jobad.feign.client.WebControllerApiClient;
import com.dotterbear.jobad.feign.model.RSSFeed;
import com.dotterbear.jobad.reader.data.model.JobAd;
import com.dotterbear.jobad.reader.html.JobsDbHtmlReader;
import com.dotterbear.jobad.rest.model.BaseResponse;

@Service
public class BatchApiService {

	@Autowired
	private WebControllerApiClient webControllerApiClient;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Value("${mongodb.bulk.insert}")
	private int bulkInsertSize;

	public ResponseEntity<BaseResponse> updateJobAds() {
		ResponseEntity<RSSFeed> response = webControllerApiClient.fetchRSSFeedUsingGET();
		if (!response.getStatusCode().equals(HttpStatus.OK)) {
			// TODO
			return new ResponseEntity<BaseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		RSSFeed rssFeed = response.getBody();
		JobsDbHtmlReader jobsDbReader = new JobsDbHtmlReader();
		List<JobAd> jobAds = jobsDbReader.buildJobAds(rssFeed);
		int size = jobAds.size();
		int endIndex = Math.min(bulkInsertSize, size);
		int startIndex = 0;
		while (startIndex < size) {
			mongoTemplate
					.bulkOps(BulkMode.UNORDERED, JobAd.class)
					.insert(jobAds.subList(startIndex, endIndex))
					.execute();
			startIndex += bulkInsertSize;
			endIndex = Math.min(endIndex + bulkInsertSize, size);
		}
		return new ResponseEntity<BaseResponse>(HttpStatus.OK);
	}

}
