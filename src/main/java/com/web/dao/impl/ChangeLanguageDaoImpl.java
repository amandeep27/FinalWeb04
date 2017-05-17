package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.web.dao.ChangeLanguageDao;
import com.web.dao.db.DBConnection;
import com.web.dao.exception.DaoException;
import com.web.domain.DBBean;
import com.web.domain.UserInfo;
import com.web.domain.UserLanguage;
import com.web.listener.ContextListener;

public class ChangeLanguageDaoImpl implements ChangeLanguageDao {

	private static DBConnection dbConnection;

	private static final Logger LOGGER = Logger.getLogger(ChangeLanguageDaoImpl.class);
	private static final String UPDATE_QUERY = "update ul_info set ul_lang_l_id = ? where ul_id = ?;";
	private static final String GET_LANG_ID = "select l_id, l_country, l_type from ul_lang where l_type = ?;";
	private static final String LANG_ID = "l_id";
	private static final String LANG_COUNTRY = "l_country";
	private static final String LANG_TYPE = "l_type";

	@Override
	public UserInfo changeUserLanguage(UserInfo userInfo, String newLanguage) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		DBBean dbBean = (DBBean) ContextListener.getApplicationContext().getAttribute("db");
		dbConnection = DBConnection.getDBInstance(dbBean);
		connection = dbConnection.createConnection(dbBean);
		UserLanguage language = getUserLanguageId(newLanguage);
		try {
			preparedStmt = connection.prepareStatement(UPDATE_QUERY);
			preparedStmt.setInt(1, language.getLangId());
			preparedStmt.setInt(2, userInfo.getUserId());
			preparedStmt.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("SQL Exception", exception);
			throw new DaoException(exception);
		} finally {
			dbConnection.closePreparedStatement(preparedStmt);
			dbConnection.closeConnection(connection);
		}
		userInfo.setLang(language);
		return userInfo;
	}

	private UserLanguage getUserLanguageId(String newLanguage) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;
		UserLanguage language = new UserLanguage();
		DBBean dbBean = (DBBean) ContextListener.getApplicationContext().getAttribute("db");
		dbConnection = DBConnection.getDBInstance(dbBean);
		connection = dbConnection.createConnection(dbBean);
		try {
			preparedStmt = connection.prepareStatement(GET_LANG_ID);
			preparedStmt.setString(1, newLanguage);
			resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				language.setLangId(resultSet.getInt(LANG_ID));
				language.setLangType(resultSet.getString(LANG_TYPE));
				language.setCountry(resultSet.getString(LANG_COUNTRY));
			}
		} catch (SQLException exception) {
			LOGGER.error("SQL Exception", exception);
			throw new DaoException(exception);
		} finally {
			dbConnection.closeResultSet(resultSet);
			dbConnection.closePreparedStatement(preparedStmt);
			dbConnection.closeConnection(connection);
		}

		return language;
	}
}
