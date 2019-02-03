package com.dotterbear.jobad.reader;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.dotterbear.jobad.reader.data.model.JobAd;
import com.dotterbear.jobad.reader.data.repo.JobAdRepository;
import com.dotterbear.jobad.reader.html.JobsDbHtmlReader;
import com.dotterbear.jobad.reader.remote.client.RssFeedClient;
import com.dotterbear.jobad.reader.remote.dto.RssFeedDto;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;

@SpringBootApplication
public class JobAdRecorder implements CommandLineRunner {

	@Autowired
	private JobAdRepository jobAdRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	private Predicate<JobAd> isNewJobAd = jobAd -> jobAd != null
			&& jobAdRepository.findByFromWebSiteAndExtRefId(jobAd.getFromWebSite(), jobAd.getExtRefId()).size() == 0;

	public static void main(String[] args) {
		SpringApplication.run(JobAdRecorder.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		List<String> links = Arrays.asList(new String[] {
//				"https://hk.jobsdb.com/hk/en/job/officer-assistant-officer-finance-100003006785941",
//				"https://hk.jobsdb.com/hk/en/job/officer-assistant-officer-finance-100003006785941",
//				"https://hk.jobsdb.com/hk/en/job/officer-assistant-officer-finance-100003006785941aaa",
//				"https://hk.jobsdb.com/hk/en/job/graduate-trainee-100003006798490"
//		});
//		JobsDbHtmlReader jobsDbReader = new JobsDbHtmlReader();
//		List<JobAd> jobAds = links.stream()
//				.map(link -> jobsDbReader.convertDocumentToJobAd(link))
//				.filter(Objects::nonNull)
//				.collect(Collectors.toList());
//		// TODO review batch insert
//		mongoTemplate.bulkOps(BulkMode.UNORDERED, JobAd.class)
//				.insert(jobAds)
//				.execute();
		RssFeedClient rssFeedClient = Feign.builder()
				  .encoder(new GsonEncoder())
				  .decoder(new GsonDecoder())
				  .logger(new Slf4jLogger(RssFeedClient.class))
				  .target(RssFeedClient.class, "http://34.73.206.62:8762/");
		RssFeedDto rssFeedDto = rssFeedClient.fetchRSSFeed();
		if (rssFeedDto == null || rssFeedDto.getChannel() == null)
			return;
		List<String> links = rssFeedDto.getChannel().getItem()
				.stream().map(item -> item.getLink()).collect(Collectors.toList());
		JobsDbHtmlReader jobsDbReader = new JobsDbHtmlReader();
		List<JobAd> jobAds = links.stream()
				.map(link -> jobsDbReader.convertDocumentToJobAd(link))
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
		// TODO review batch insert
		mongoTemplate.bulkOps(BulkMode.UNORDERED, JobAd.class)
				.insert(jobAds)
				.execute();
		System.out.println(rssFeedDto);
		
	}

}
