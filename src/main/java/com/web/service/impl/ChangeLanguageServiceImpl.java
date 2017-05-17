package com.web.service.impl;

import org.apache.log4j.Logger;

import com.web.dao.ChangeLanguageDao;
import com.web.dao.exception.DaoException;
import com.web.dao.factory.DaoFactory;
import com.web.domain.UserInfo;
import com.web.domain.UserLanguage;
import com.web.service.ChangeLanguageService;
import com.web.service.exception.ServiceException;

public class ChangeLanguageServiceImpl implements ChangeLanguageService {

	private static DaoFactory daoFactoryInstance;
	private static ChangeLanguageDao changeLanguageDao;

	private static final Logger LOGGER = Logger.getLogger(ChangeLanguageServiceImpl.class);

	@Override
	public UserInfo changeUserLanguage(UserInfo userInfo, String newLanguage) throws ServiceException {
		daoFactoryInstance = DaoFactory.getDaoFactoryInstance();
		changeLanguageDao = daoFactoryInstance.changeLanguageDaoInstance();
		if (!userInfo.getLang().getLangType().equals(newLanguage)) {
			try {
				userInfo = changeLanguageDao.changeUserLanguage(userInfo, newLanguage);
			} catch (DaoException exception) {
				LOGGER.error("DAO Exception");
				throw new ServiceException(exception);
			}
		}
		return userInfo;
	}

}
