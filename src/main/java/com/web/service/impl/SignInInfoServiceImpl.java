package com.web.service.impl;

import org.apache.log4j.Logger;
import com.web.dao.SignInInfoDao;
import com.web.dao.exception.DaoException;
import com.web.dao.factory.DaoFactory;
import com.web.domain.UserInfo;
import com.web.dto.SignInInfo;
import com.web.listener.ContextListener;
import com.web.service.PasswordHashing;
import com.web.service.SignInInfoService;
import com.web.service.exception.ServiceException;

public class SignInInfoServiceImpl implements SignInInfoService {
	private static PasswordHashing pwdHashing;
	private static DaoFactory daoFactoryInstance;
	private static SignInInfoDao signInInfoDaoInstance;
	private static UserInfo userInfo;
	private static final Logger LOGGER = Logger.getLogger(SignInInfoServiceImpl.class);

	@Override
	public boolean checksIfValidUser(SignInInfo signInInfo) throws ServiceException {
		boolean validUser = false;

		daoFactoryInstance = DaoFactory.getDaoFactoryInstance();
		signInInfoDaoInstance = daoFactoryInstance.getSignInInfoDaoInstance();
		try {
			validUser = signInInfoDaoInstance.checksIfValidUser(signInInfo);
		} catch (DaoException exception) {
			LOGGER.error("Dao Exception");
			throw new ServiceException(exception);
		}

		return validUser;
	}

	@Override
	public UserInfo getUserInfoById(SignInInfo signInInfo) throws ServiceException {
		daoFactoryInstance = DaoFactory.getDaoFactoryInstance();
		signInInfoDaoInstance = daoFactoryInstance.getSignInInfoDaoInstance();
		try {
			userInfo = signInInfoDaoInstance.getUserInfoById(signInInfo);
		} catch (DaoException exception) {
			LOGGER.error("Dao Exception");
			throw new ServiceException(exception);
		}
		return userInfo;
	}

	@Override
	public boolean checkIfPasswordMatches(SignInInfo signInInfo, String password) throws ServiceException {
		boolean validUser = false;
		signInInfo = changePwdToHashedPwd(signInInfo);
		if (signInInfo.getPassword().equals(password)) {
			validUser = true;
		}
		return validUser;
	}

	private SignInInfo changePwdToHashedPwd(SignInInfo signInInfo) throws ServiceException {
		pwdHashing = new PasswordHashingServiceImpl();
		String pwd = signInInfo.getPassword();
		String magicLine = (String) ContextListener.getApplicationContext().getAttribute("magic");
		pwd = pwd + magicLine;
		signInInfo.setPassword(pwdHashing.generateHash(pwd));
		return signInInfo;
	}

}
