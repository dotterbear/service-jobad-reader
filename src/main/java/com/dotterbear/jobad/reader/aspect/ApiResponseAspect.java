package com.dotterbear.jobad.reader.aspect;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ApiResponseAspect {

  private static final Logger log = LoggerFactory.getLogger(ApiResponseAspect.class);

  @Around(
      value = "@within(com.dotterbear.jobad.reader.rest.api.ApiEndpoint) || @annotation(com.dotterbear.jobad.reader.rest.api.ApiEndpoint)")
  public Object aroundApiEndpoint(ProceedingJoinPoint joinPoint) throws Throwable {
    Object obj = joinPoint.proceed();
    if (!(obj instanceof ResponseEntity))
      return obj;
    try {
      ResponseEntity<?> response = (ResponseEntity<?>) obj;
      if (response == null || response.getBody() == null)
        return response;
      Object responseBody = response.getBody();
      Class<? extends Object> bodyClass = responseBody.getClass();
      Method setCode = bodyClass.getMethod("setCode", String.class);
      Method setMessage = bodyClass.getMethod("setMessage", String.class);
      setCode.invoke(responseBody, "200");
      setMessage.invoke(responseBody, "OK");
    } catch (Exception e) {
      log.warn("unable to set default OK code, message to response, joinPoint.getSignature(): {}",
          joinPoint.getSignature(), e);
    }
    return obj;
  }

}
