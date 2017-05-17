package com.web.service;

import com.web.domain.UserInfo;
import com.web.dto.SignInInfo;
import com.web.service.exception.ServiceException;

public interface SignInInfoService {
	boolean checksIfValidUser(SignInInfo signInInfo) throws ServiceException;

	UserInfo getUserInfoById(SignInInfo signInInfo) throws ServiceException;

	boolean checkIfPasswordMatches(SignInInfo signInInfo, String password) throws ServiceException;
}
