package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.web.dao.SignInInfoDao;
import com.web.dao.db.DBConnection;
import com.web.dao.exception.DaoException;
import com.web.domain.DBBean;
import com.web.domain.UserInfo;
import com.web.domain.UserLanguage;
import com.web.dto.SignInInfo;
import com.web.listener.ContextListener;

public class SignInInfoDaoImpl implements SignInInfoDao {

	private static DBConnection dbConnection;
	private static final Logger LOGGER = Logger.getLogger(SignInInfoDaoImpl.class);
	private static final String SELECT_QUERY = "select * from user_login.ul_info where ul_id = ?;";
	private static final String IF_VALID_USER = "select exists( select * from user_login.ul_info where ul_id = ?) as 'userExists';";
	private static final String GET_USER_LANG = "select * from ul_lang where l_id = ?;";
	private static final String USER_ID = "ul_id";
	private static final String USER_NAME = "ul_name";
	private static final String PWD = "ul_pwd";
	private static final String ROLE = "ul_role";
	private static final String LANG = "ul_lang_l_id";
	private static final String VALID_USER = "userExists";
	private static final String LANG_ID = "l_id";
	private static final String LANG_TYPE = "l_type";
	private static final String LANG_COUNTRY = "l_country";

	@Override
	public UserInfo getUserInfoById(SignInInfo signInInfo) throws DaoException {
		UserInfo userInfo = new UserInfo();
		UserLanguage userLanguage = new UserLanguage();
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;

		DBBean dbBean = (DBBean) ContextListener.getApplicationContext().getAttribute("db");
		dbConnection = DBConnection.getDBInstance(dbBean);
		connection = dbConnection.createConnection(dbBean);
		try {
			preparedStmt = connection.prepareStatement(SELECT_QUERY);
			preparedStmt.setInt(1, signInInfo.getUserId());
			resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				userInfo.setUserId(Integer.parseInt(resultSet.getString(USER_ID)));
				userInfo.setUserName(resultSet.getString(USER_NAME));
				userInfo.setPassword(resultSet.getString(PWD));
				userInfo.setUserRole(resultSet.getString(ROLE));
				userLanguage = getUserLanguageId(resultSet.getInt(LANG));
				userInfo.setLang(userLanguage);
			}
		} catch (SQLException exception) {
			LOGGER.error("SQL Exception", exception);
			throw new DaoException(exception);
		} finally {
			dbConnection.closeResultSet(resultSet);
			dbConnection.closePreparedStatement(preparedStmt);
			dbConnection.closeConnection(connection);
		}

		return userInfo;
	}

	private UserLanguage getUserLanguageId(int langId) throws DaoException {
		UserLanguage userLang = new UserLanguage();
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;
		DBBean dbBean = (DBBean) ContextListener.getApplicationContext().getAttribute("db");
		dbConnection = DBConnection.getDBInstance(dbBean);
		connection = dbConnection.createConnection(dbBean);
		try {
			preparedStmt = connection.prepareStatement(GET_USER_LANG);
			preparedStmt.setInt(1, langId);
			resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				userLang.setLangId(resultSet.getInt(LANG_ID));
				userLang.setLangType(resultSet.getString(LANG_TYPE));
				userLang.setCountry(resultSet.getString(LANG_COUNTRY));
			}
		} catch (SQLException exception) {
			LOGGER.error("SQL Exception", exception);
			throw new DaoException(exception);
		} finally {
			dbConnection.closeResultSet(resultSet);
			dbConnection.closePreparedStatement(preparedStmt);
			dbConnection.closeConnection(connection);
		}

		return userLang;
	}

	@Override
	public boolean checksIfValidUser(SignInInfo signInInfo) throws DaoException {
		boolean isValidUser = false;
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;
		int validUser = 0;
		DBBean dbBean = (DBBean) ContextListener.getApplicationContext().getAttribute("db");
		dbConnection = DBConnection.getDBInstance(dbBean);
		connection = dbConnection.createConnection(dbBean);
		try {
			preparedStmt = connection.prepareStatement(IF_VALID_USER);
			preparedStmt.setInt(1, signInInfo.getUserId());
			resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				validUser = resultSet.getInt(VALID_USER);
			}
		} catch (SQLException exception) {
			LOGGER.info("SQL Exception", exception);
			throw new DaoException(exception);
		} finally {
			dbConnection.closeResultSet(resultSet);
			dbConnection.closePreparedStatement(preparedStmt);
			dbConnection.closeConnection(connection);
		}

		if (validUser != 0) {
			isValidUser = true;
		}
		return isValidUser;
	}

}
