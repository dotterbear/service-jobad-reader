package com.dotterbear.jobad.reader.rest.api;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dotterbear.jobad.reader.rest.service.JobAdApiService;
import com.dotterbear.jobad.rest.api.JobadApi;
import com.dotterbear.jobad.rest.model.CompanyNameListResponse;
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
      @ApiParam(value = "search in title or job details") @Valid @RequestParam(value = "query",
          required = false) String query,
      @ApiParam(value = "search by company name, full match") @Valid @RequestParam(
          value = "company-name", required = false) String companyName,
      @ApiParam(value = "search by tags, using or case of the passed list") @Valid @RequestParam(
          value = "tags", required = false) List<String> tags,
      @ApiParam(value = "search by job title, full match") @Valid @RequestParam(value = "title",
          required = false) String title) {
    log.debug(
        "findJobAds, size: {}, page: {}, direction: {}, orderBy: {}, query: {}, companyName: {}, tags: {}, title: {}",
        size, page, direction, orderBy, query, companyName, tags, title);
    return jobAdApiService.findJobAds(size, page, direction, orderBy, query, companyName, tags, title);
  }

  @Override
  @ApiEndpoint
  public ResponseEntity<CompanyNameListResponse> getCompanyNameList() {
    return jobAdApiService.getCompanyNameList();
  }

}
