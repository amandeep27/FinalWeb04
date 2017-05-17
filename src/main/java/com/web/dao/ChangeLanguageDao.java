package com.web.dao;

import com.web.dao.exception.DaoException;
import com.web.domain.UserInfo;

public interface ChangeLanguageDao {

	UserInfo changeUserLanguage(UserInfo userInfo, String newLanguage) throws DaoException;

}
