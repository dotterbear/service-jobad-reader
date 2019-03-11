package com.dotterbear.jobad.reader.data.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;
import com.dotterbear.jobad.reader.utils.DataUtils;

@Document(collection = "JobAd")
public class JobAd {

  @Id
  private String id;

  private String companyName;

  private String companyNameRaw;

  private String companyProfile;

  private String companyProfileRaw;

  private String title;

  private String titleRaw;

  private String details;

  private String detailsRaw;

  private String industry;

  private String careerLevel;

  private String qualification;

  private String location;

  private String employmentType;

  private String others;

  private String url;

  private String extRefId;

  private Integer yearsOfExp;

  private Integer salary;

  private Set<String> benefits;

  private Date postedDate;

  private Date ts;

  private WebSiteEnum fromWebSite;

  private Set<String> tags;

  @TextScore
  private Float score;

  public JobAd() {
    tags = new HashSet<String>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCompanyName() {
    return companyName;
  }

  public JobAd setCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  public String getCompanyNameRaw() {
    return companyNameRaw;
  }

  public JobAd setCompanyNameRaw(String companyNameRaw) {
    this.companyNameRaw = companyNameRaw;
    return this;
  }

  public String getCompanyProfile() {
    return companyProfile;
  }

  public JobAd setCompanyProfile(String companyProfile) {
    this.companyProfile = companyProfile;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public JobAd setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getTitleRaw() {
    return titleRaw;
  }

  public JobAd setTitleRaw(String titleRaw) {
    this.titleRaw = titleRaw;
    return this;
  }

  public String getDetails() {
    return details;
  }

  public JobAd setDetails(String details) {
    this.details = details;
    return this;
  }

  public String getIndustry() {
    return industry;
  }

  public JobAd setIndustry(String industry) {
    this.industry = industry;
    return this;
  }

  public String getCareerLevel() {
    return careerLevel;
  }

  public JobAd setCareerLevel(String careerLevel) {
    this.careerLevel = careerLevel;
    return this;
  }

  public String getQualification() {
    return qualification;
  }

  public JobAd setQualification(String qualification) {
    this.qualification = qualification;
    return this;
  }

  public String getLocation() {
    return location;
  }

  public JobAd setLocation(String location) {
    this.location = location;
    return this;
  }

  public String getEmploymentType() {
    return employmentType;
  }

  public JobAd setEmploymentType(String employmentType) {
    this.employmentType = employmentType;
    return this;
  }

  public String getOthers() {
    return others;
  }

  public JobAd setOthers(String others) {
    this.others = others;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public JobAd setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getExtRefId() {
    return extRefId;
  }

  public JobAd setExtRefId(String extRefId) {
    this.extRefId = extRefId;
    return this;
  }

  public Integer getYearsOfExp() {
    return yearsOfExp;
  }

  public JobAd setYearsOfExp(Integer yearOfExp) {
    this.yearsOfExp = yearOfExp;
    return this;
  }

  public Integer getSalary() {
    return salary;
  }

  public JobAd setSalary(Integer salary) {
    this.salary = salary;
    return this;
  }

  public Set<String> getBenefits() {
    return benefits;
  }

  public JobAd setBenefits(Set<String> benefits) {
    this.benefits = benefits;
    return this;
  }

  public Date getTs() {
    return ts;
  }

  public JobAd setTs(Date ts) {
    this.ts = ts;
    return this;
  }

  public Date getPostedDate() {
    return postedDate;
  }

  public JobAd setPostedDate(Date postedDate) {
    this.postedDate = postedDate;
    return this;
  }

  public WebSiteEnum getFromWebSite() {
    return fromWebSite;
  }

  public JobAd setFromWebSite(WebSiteEnum fromWebSite) {
    this.fromWebSite = fromWebSite;
    return this;
  }

  public String getCompanyProfileRaw() {
    return companyProfileRaw;
  }

  public JobAd setCompanyProfileRaw(String companyProfileRaw) {
    this.companyProfileRaw = companyProfileRaw;
    return this;
  }

  public String getDetailsRaw() {
    return detailsRaw;
  }

  public JobAd setDetailsRaw(String detailsRaw) {
    this.detailsRaw = detailsRaw;
    return this;
  }

  public Float getScore() {
    return score;
  }

  public JobAd setScore(Float score) {
    this.score = score;
    return this;
  }

  public Set<String> getTags() {
    return tags;
  }

  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  public JobAd addTags(String tag) {
    if (DataUtils.isEmpty(tag))
      return this;
    tags.add(DataUtils.toLowerCaseAndTrim(tag));
    return this;
  }

  public JobAd addTags(Collection<String> tags) {
    if (DataUtils.isEmpty(tags))
      return this;
    this.tags.addAll(tags.stream().filter(DataUtils::isEmpty).map(DataUtils::toLowerCaseAndTrim)
        .collect(Collectors.toSet()));
    return this;
  }

  @Override
  public String toString() {
    return "JobAd [id=" + id + ", companyName=" + companyName + ", companyNameRaw=" + companyNameRaw
        + ", companyProfile=" + companyProfile + ", companyProfileRaw=" + companyProfileRaw
        + ", title=" + title + ", titleRaw=" + titleRaw + ", details=" + details + ", detailsRaw="
        + detailsRaw + ", industry=" + industry + ", careerLevel=" + careerLevel
        + ", qualification=" + qualification + ", location=" + location + ", employmentType="
        + employmentType + ", others=" + others + ", url=" + url + ", extRefId=" + extRefId
        + ", yearsOfExp=" + yearsOfExp + ", salary=" + salary + ", benefits=" + benefits
        + ", postedDate=" + postedDate + ", ts=" + ts + ", fromWebSite=" + fromWebSite + ", tags="
        + tags + ", score=" + score + "]";
  }

}
