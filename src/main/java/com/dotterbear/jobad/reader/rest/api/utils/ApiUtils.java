package com.dotterbear.jobad.reader.rest.api.utils;

import com.dotterbear.jobad.rest.model.BaseResponse;

public class ApiUtils {

  public static BaseResponse buildBaseResponse(Exception e) {
    return new BaseResponse().code("999").message(e.getMessage());
  }

}
