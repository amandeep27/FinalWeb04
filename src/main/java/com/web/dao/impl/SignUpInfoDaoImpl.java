package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.web.dao.SignUpInfoDao;
import com.web.dao.db.DBConnection;
import com.web.dao.exception.DaoException;
import com.web.domain.DBBean;
import com.web.dto.SignUpInfo;
import com.web.listener.ContextListener;

public class SignUpInfoDaoImpl implements SignUpInfoDao {

	private static DBConnection dbConnection;
	private static final Logger LOGGER = Logger.getLogger(SignUpInfoDaoImpl.class);

	private static final String INSERT_QUERY = "insert into ul_info values(?, ?, ?, ?, ?);";
	private static final String GET_LANG_ID = "select l_id from ul_lang where l_type = ?;";
	private static final String LANG_ID = "l_id";

	@Override
	public boolean saveInfoInDB(SignUpInfo signUpInfo) throws DaoException {
		boolean flag = false;
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		int affectedRows = 0;
		DBBean dbBean = (DBBean) ContextListener.getApplicationContext().getAttribute("db");
		dbConnection = DBConnection.getDBInstance(dbBean);
		connection = dbConnection.createConnection(dbBean);
		int langId = getUserLanguageId(signUpInfo);
		try {
			preparedStmt = connection.prepareStatement(INSERT_QUERY);
			preparedStmt.setInt(1, signUpInfo.getUserId());
			preparedStmt.setString(2, signUpInfo.getUserName());
			preparedStmt.setString(3, signUpInfo.getPassword());
			preparedStmt.setString(4, signUpInfo.getUserRole());
			preparedStmt.setInt(5, langId);
			affectedRows = preparedStmt.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("SQL Exception", exception);
			throw new DaoException(exception);
		} finally {
			dbConnection.closePreparedStatement(preparedStmt);
			dbConnection.closeConnection(connection);
		}
		LOGGER.info("sign up info : " + signUpInfo);
		if (affectedRows > 0) {
			flag = true;
		}
		return flag;
	}

	private int getUserLanguageId(SignUpInfo signUpInfo) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;
		int langID = 0;
		DBBean dbBean = (DBBean) ContextListener.getApplicationContext().getAttribute("db");
		dbConnection = DBConnection.getDBInstance(dbBean);
		connection = dbConnection.createConnection(dbBean);
		try {
			preparedStmt = connection.prepareStatement(GET_LANG_ID);
			preparedStmt.setString(1, signUpInfo.getLanguage());
			resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				langID = resultSet.getInt(LANG_ID);
			}
		} catch (SQLException exception) {
			LOGGER.error("SQL Exception", exception);
			throw new DaoException(exception);
		} finally {
			dbConnection.closeResultSet(resultSet);
			dbConnection.closePreparedStatement(preparedStmt);
			dbConnection.closeConnection(connection);
		}

		return langID;
	}

}
