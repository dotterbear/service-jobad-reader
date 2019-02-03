package com.dotterbear.jobad.reader.remote.client;

import com.dotterbear.jobad.reader.remote.dto.RssFeedDto;

import feign.RequestLine;

public interface RssFeedClient {

	@RequestLine("GET /fetchRSSFeed")
	RssFeedDto fetchRSSFeed();

}
