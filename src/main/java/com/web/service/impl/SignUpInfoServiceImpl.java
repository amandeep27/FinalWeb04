package com.web.service.impl;

import org.apache.log4j.Logger;
import com.web.dao.SignUpInfoDao;
import com.web.dao.exception.DaoException;
import com.web.dao.factory.DaoFactory;
import com.web.dto.SignUpInfo;
import com.web.listener.ContextListener;
import com.web.service.PasswordHashing;
import com.web.service.SignUpInfoService;
import com.web.service.exception.ServiceException;
import com.web.service.impl.validator.SignUpInfoValidator;

public class SignUpInfoServiceImpl implements SignUpInfoService {
	private static DaoFactory daoFactoryInstance;
	private static SignUpInfoValidator validator;
	private static SignUpInfoDao signUpInfoDaoInstance;
	private static PasswordHashing hashing;

	private static final Logger LOGGER = Logger.getLogger(SignUpInfoServiceImpl.class);

	@Override
	public boolean saveInfoInDB(SignUpInfo signUpInfo) throws ServiceException {
		boolean flag;
		validator = SignUpInfoValidator.getSignUpInfoValidator();
		validator.checkBothPasswords(signUpInfo.getPassword(), signUpInfo.getRepeatPassword());
		signUpInfo = changePwdToHashedPwd(signUpInfo);
		daoFactoryInstance = DaoFactory.getDaoFactoryInstance();
		signUpInfoDaoInstance = daoFactoryInstance.getSignUpInfoDaoInstance();
		try {
			flag = signUpInfoDaoInstance.saveInfoInDB(signUpInfo);
		} catch (DaoException exception) {
			LOGGER.error("Dao Exception");
			throw new ServiceException(exception);
		}
		return flag;
	}

	private SignUpInfo changePwdToHashedPwd(SignUpInfo signUpInfo) throws ServiceException {
		hashing = new PasswordHashingServiceImpl();
		String pwd = signUpInfo.getPassword();
		String magicLine = (String) ContextListener.getApplicationContext().getAttribute("magic");
		pwd = pwd + magicLine;
		signUpInfo.setPassword(hashing.generateHash(pwd));
		return signUpInfo;
	}

}
