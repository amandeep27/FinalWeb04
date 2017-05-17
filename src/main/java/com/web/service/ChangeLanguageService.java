package com.web.service;

import com.web.domain.UserInfo;
import com.web.service.exception.ServiceException;

public interface ChangeLanguageService {

	UserInfo changeUserLanguage(UserInfo userInfo, String newLanguage) throws ServiceException;

}
