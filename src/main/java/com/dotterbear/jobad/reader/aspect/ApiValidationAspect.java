package com.dotterbear.jobad.reader.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dotterbear.jobad.reader.rest.api.utils.ApiUtils;

@Component
@Aspect
public class ApiValidationAspect {

	private static final Logger log = LoggerFactory.getLogger(ApiValidationAspect.class);

	@Before("execution(* com.dotterbear.jobad.reader.rest.api.JobAdApiImpl.findJobAds(..))")
	public void validateJobAdListRequestBefore(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		log.debug("args: {}", args);
		if (args.length < 5)
			return;
		String orderBy = (String) args[2];
		String direction = (String) args[3];
		String query = (String) args[4];
		if ("score".equals(orderBy)) {
			if (ApiUtils.isEmptyString(query))
				throw new IllegalArgumentException("unable to order by score with query string");
			else if ("desc".equals(direction))
				throw new IllegalArgumentException("unable to order by score in descending order");
		}
	}

}
