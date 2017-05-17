package com.web.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.web.dao.exception.DaoException;
import com.web.domain.DBBean;

public class DBConnection {
	private static volatile DBConnection connection;

	private static final Logger LOGGER = Logger.getLogger(DBConnection.class);

	private DBConnection(DBBean dbBean) {
		try {
			Class.forName(dbBean.getDriver());
		} catch (ClassNotFoundException exception) {
			LOGGER.fatal(exception);
			throw new RuntimeException(exception);
		}
	}

	public static DBConnection getDBInstance(DBBean db) {
		if (connection == null) {
			synchronized (DBConnection.class) {
				if (connection == null) {
					connection = new DBConnection(db);
				}
			}
		}
		return connection;
	}

	public Connection createConnection(DBBean bean) throws DaoException {
		try {
			if (bean.getUserName() == null || bean.getPassword() == null) {
				return DriverManager.getConnection(bean.getUrl());
			} else {
				return DriverManager.getConnection(bean.getUrl(), bean.getUserName(), bean.getPassword());
			}
		} catch (SQLException exception) {
			LOGGER.error("SQL Exception", exception);
			throw new DaoException(exception);
		}
	}

	public void closeConnection(Connection connection) throws DaoException {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException exception) {
			LOGGER.error("SQL Exception", exception);
			throw new DaoException(exception);
		}

	}

	public void closePreparedStatement(PreparedStatement preparedStatement) throws DaoException {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException exception) {
			LOGGER.error("SQL Exception", exception);
			throw new DaoException(exception);
		}

	}

	public void closeResultSet(ResultSet resultSet) throws DaoException {

		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException exception) {
			LOGGER.error("SQL Exception", exception);
			throw new DaoException(exception);
		}

	}

}
