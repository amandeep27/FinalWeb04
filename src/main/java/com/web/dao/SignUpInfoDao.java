package com.web.dao;

import com.web.dao.exception.DaoException;
import com.web.dto.SignUpInfo;

public interface SignUpInfoDao {

	boolean saveInfoInDB(SignUpInfo signUpInfo) throws DaoException;
}
