package com.dotterbear.jobad.reader.data.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;
import com.dotterbear.jobad.reader.data.model.JobAd;
import com.dotterbear.jobad.reader.data.repo.JobAdRepository;
import com.dotterbear.jobad.reader.utils.DataUtils;

@Service
public class JobAdService {

  private static final Logger log = LoggerFactory.getLogger(JobAdService.class);

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private JobAdRepository jobAdRepository;

  public JobAd findById(String id) {
    log.debug("findById, id: {}", id);
    Optional<JobAd> jobAd = jobAdRepository.findById(id);
    return jobAd.isPresent() ? jobAd.get() : null;
  }

  public Page<JobAd> findAll(int page, int size, String direction, String orderBy) {
    log.debug("findAllOrderByTs, page: {}, size: {}, direction: {}, orderby: {}", page, size,
        direction, orderBy);
    return jobAdRepository
        .findAll(PageRequest.of(page - 1, size, Sort.by(buildSortDirection(direction), orderBy)));
  }

  public Page<JobAd> searchByQuery(int page, int size, String direction, String orderBy,
      String query, String companyName, List<String> tags, String title) {
    log.debug("findAllOrderByTs, page: {}, size: {}, direction: {}, orderby: {}, query: {}, companyName: {}, tags: {}, title: {}", page,
        size, direction, orderBy, query, companyName, tags, title);
    Pageable pagable = PageRequest.of(page - 1, size, Sort.by(direction, orderBy));
    Query dbQuery = new Query().with(pagable);
    // TODO make a builder ?
    if (!DataUtils.isEmpty(query))
      dbQuery.addCriteria(TextCriteria.forDefaultLanguage().matchingAny(query));
    if (!DataUtils.isEmpty(companyName))
      dbQuery.addCriteria(Criteria.where("companyName").is(companyName));
    if (!DataUtils.isEmpty(tags))
      dbQuery.addCriteria(Criteria.where("tags").in(tags));
    if (!DataUtils.isEmpty(title))
      dbQuery.addCriteria(Criteria.where("title").in(tags));
    long count = mongoTemplate.count(dbQuery, JobAd.class);
    List<JobAd> jobAds = mongoTemplate.find(dbQuery, JobAd.class);
    return new PageImpl<JobAd>(jobAds, pagable, count);
  }

  public List<String> findDistinctCompanyNames() {
    return mongoTemplate.query(JobAd.class).distinct("companyName").as(String.class).all();
  }

  private Direction buildSortDirection(String direction) {
    return direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
  }

}
