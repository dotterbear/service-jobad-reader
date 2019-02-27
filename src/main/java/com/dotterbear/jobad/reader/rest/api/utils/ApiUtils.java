package com.dotterbear.jobad.reader.rest.api.utils;

import com.dotterbear.jobad.rest.model.BaseResponse;

public class ApiUtils {

	public static boolean isEmptyString(String str) {
		return str == null || str.isEmpty();
	}

	public static BaseResponse buildBaseResponse(RuntimeException e) {
		return new BaseResponse()
				.code("9999")
				.message(e.getMessage());
	}
}
