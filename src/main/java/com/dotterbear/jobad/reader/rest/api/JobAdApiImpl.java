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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dotterbear.jobad.reader.rest.service.JobAdApiService;
import com.dotterbear.jobad.rest.api.JobadApi;
import com.dotterbear.jobad.rest.model.JobAdDetailResponse;
import com.dotterbear.jobad.rest.model.JobAdListResponse;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1")
public class JobAdApiImpl implements JobadApi {

  private static final Logger log = LoggerFactory.getLogger(JobAdApiImpl.class);

  @Autowired
  private JobAdApiService jobAdApiService;

  @Override
  @ApiEndpoint
  public ResponseEntity<JobAdDetailResponse> findJobAdById(
      @ApiParam(value = "id of jobad", required = true) @PathVariable("id") String id) {
    log.debug("findJobAdById(String id), id: {}", id);
    return jobAdApiService.findJobAdById(id);
  }

  @Override
  @ApiEndpoint
  public ResponseEntity<JobAdListResponse> findJobAds(
      @Min(1) @Max(100) @ApiParam(
          value = "limit size of the items, min is 1, max is 100, default is 25",
          defaultValue = "25") @Valid @RequestParam(value = "size", required = false,
              defaultValue = "25") Integer size,
      @Min(1) @ApiParam(value = "page no. of the query", defaultValue = "1") @Valid @RequestParam(
          value = "page", required = false, defaultValue = "1") Integer page,
      @ApiParam(value = "order by fields", allowableValues = "title, score, postedDate",
          defaultValue = "postedDate") @Valid @RequestParam(value = "orderBy", required = false,
              defaultValue = "postedDate") String orderBy,
      @ApiParam(value = "order by fields", allowableValues = "asc, desc",
          defaultValue = "desc") @Valid @RequestParam(value = "direction", required = false,
              defaultValue = "desc") String direction,
      @ApiParam(value = "search by title or job details") @Valid @RequestParam(value = "query",
          required = false) String query) {
    log.debug("findJobAds, size: {}, page: {}", size, page);
    return jobAdApiService.findJobAds(size, page, direction, orderBy, query);
  }

}
