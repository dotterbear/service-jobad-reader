package com.dotterbear.jobad.reader.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dotterbear.jobad.reader.rest.service.BatchApiService;
import com.dotterbear.jobad.rest.api.BatchApi;
import com.dotterbear.jobad.rest.model.BaseResponse;

@RestController
@RequestMapping("/api/v1")
public class BatchApiImpl implements BatchApi {

	@Autowired
	private BatchApiService batchApiService;

	public ResponseEntity<BaseResponse> updateJobAds() {
		try {
			return batchApiService.updateJobAds();
		} catch (Exception e) {
			// TODO
			ResponseEntity<BaseResponse> response = new ResponseEntity<BaseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
}
