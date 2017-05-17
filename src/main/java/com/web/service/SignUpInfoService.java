package com.web.service;

import com.web.dto.SignUpInfo;
import com.web.service.exception.ServiceException;

public interface SignUpInfoService {
	boolean saveInfoInDB(SignUpInfo signUpInfo) throws ServiceException;
}
