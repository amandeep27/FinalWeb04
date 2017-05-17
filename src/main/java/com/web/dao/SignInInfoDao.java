package com.web.dao;

import com.web.dao.exception.DaoException;
import com.web.domain.UserInfo;
import com.web.dto.SignInInfo;

public interface SignInInfoDao {

	UserInfo getUserInfoById(SignInInfo signInInfo) throws DaoException;

	boolean checksIfValidUser(SignInInfo signInInfo) throws DaoException;
}
