package com.dotterbear.jobad.reader.html;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.dotterbear.jobad.feign.model.RSSFeed;
import com.dotterbear.jobad.reader.data.model.JobAd;

public interface HtmlReader {

	public String getUserAgent();

	public int getTimeout();

	public boolean isDocumentOk(Document document);

	public List<JobAd> buildJobAds(RSSFeed rssFeed);

	public default Document fetchDocument(String url) throws IOException {
		Document document = Jsoup.connect(url)
				.userAgent(getUserAgent())
				.timeout(getTimeout())
				.get();
		if (!isDocumentOk(document)) {
			throw new IOException("invalid response");
		}
		return document;
	}

}
