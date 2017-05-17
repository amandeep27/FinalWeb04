package com.web.dao.factory;

import com.web.dao.BookInfoDao;
import com.web.dao.ChangeLanguageDao;
import com.web.dao.SignInInfoDao;
import com.web.dao.SignUpInfoDao;
import com.web.dao.impl.BookInfoDaoImpl;
import com.web.dao.impl.ChangeLanguageDaoImpl;
import com.web.dao.impl.SignInInfoDaoImpl;
import com.web.dao.impl.SignUpInfoDaoImpl;

public class DaoFactory {

	private static volatile DaoFactory daoFactoryInstance;

	private DaoFactory() {

	}

	public static DaoFactory getDaoFactoryInstance() {
		if (daoFactoryInstance == null) {
			synchronized (DaoFactory.class) {
				if (daoFactoryInstance == null) {
					daoFactoryInstance = new DaoFactory();
				}
			}
		}
		return daoFactoryInstance;
	}

	public SignUpInfoDao getSignUpInfoDaoInstance() {
		return new SignUpInfoDaoImpl();
	}

	public SignInInfoDao getSignInInfoDaoInstance() {
		return new SignInInfoDaoImpl();
	}

	public BookInfoDao getBookInfoDaoInstance() {
		return new BookInfoDaoImpl();
	}

	public ChangeLanguageDao changeLanguageDaoInstance() {
		return new ChangeLanguageDaoImpl();
	}
}
