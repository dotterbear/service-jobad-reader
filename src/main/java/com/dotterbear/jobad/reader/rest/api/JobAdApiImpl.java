package com.dotterbear.jobad.reader.rest.api;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dotterbear.jobad.feign.client.WebControllerApiClient;
import com.dotterbear.jobad.feign.model.RSSFeed;
import com.dotterbear.jobad.reader.rest.service.JobAdApiService;
import com.dotterbear.jobad.rest.api.JobadApi;
import com.dotterbear.jobad.rest.model.JobAdItem;
import com.dotterbear.jobad.rest.model.JobAdList;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1")
public class JobAdApiImpl implements JobadApi {

	private static final Logger log = LoggerFactory.getLogger(JobAdApiImpl.class);

	@Autowired
	private JobAdApiService jobAdApiService;

	@Autowired
	private WebControllerApiClient webControllerApiClient;

	@Override
	public ResponseEntity<JobAdItem> findJobAdById(@ApiParam(value = "id of jobad",required=true) @PathVariable("id") String id) {
		log.debug("findJobAdById(String id), id: {}", id);
		try {
			return jobAdApiService.findJobAdById(id);
		} catch (Exception e) {
			// TODO
			ResponseEntity<JobAdItem> response = new ResponseEntity<JobAdItem>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}

	@Override
	public ResponseEntity<JobAdList> findJobAds(@Min(1) @Max(100) @ApiParam(value = "limit size of the items, min is 1, max is 100, default is 25", defaultValue = "25") @Valid @RequestParam(value = "size", required = false, defaultValue="25") Integer size,@Min(1)@ApiParam(value = "page no. of the query", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page) {
		log.debug("findJobAds, size: {}, page: {}", size, page);
		try {
			return jobAdApiService.findJobAds(size, page);
		} catch (Exception e) {
			// TODO
			ResponseEntity<JobAdList> response = new ResponseEntity<JobAdList>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}

	@RequestMapping(value = "/jobad/test", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<String> test() {
		ResponseEntity<RSSFeed> response = webControllerApiClient.fetchRSSFeedUsingGET();
		RSSFeed rssFeed = response.getBody();
		System.out.println(rssFeed);
		return null;
	}
}
