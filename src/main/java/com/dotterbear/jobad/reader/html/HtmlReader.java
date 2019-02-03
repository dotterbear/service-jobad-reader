package com.dotterbear.jobad.reader.html;

import com.dotterbear.jobad.reader.data.model.JobAd;

public interface HtmlReader {

	public JobAd convertDocumentToJobAd(String link);

}
