package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.web.dao.BookInfoDao;
import com.web.dao.db.DBConnection;
import com.web.dao.exception.DaoException;
import com.web.domain.BookAuthor;
import com.web.domain.BookInfo;
import com.web.domain.BookType;
import com.web.domain.DBBean;
import com.web.domain.UserInfo;
import com.web.dto.GetBookInfo;
import com.web.listener.ContextListener;

public class BookInfoDaoImpl implements BookInfoDao {

	private static DBConnection dbConnection;
	private static UserInfo userInfo;
	private static BookInfo bookInfo;
	private static BookAuthor author;
	private static BookType type;

	private static final Logger LOGGER = Logger.getLogger(BookInfoDaoImpl.class);
	private static final String BOOK_INFO_ID = "book_info.bi_id";
	private static final String BOOK_INFO_TITLE = "book_info.bi_title";
	private static final String BOOK_INFO_DESC = "book_info.bi_desc";
	private static final String BOOK_INFO_PUBLISH_YEAR = "book_info.bi_publish_year";
	private static final String TYPE_NAME = "type_name";
	private static final String AUTHOR_NAME = "author_name";

	private static boolean isTypeSet = false;
	private static boolean isTitleSet = false;
	private static boolean isAuthorSet = false;

	@Override
	public List<BookInfo> getBookInfo(GetBookInfo getBookInfo) throws DaoException {
		LOGGER.info(getBookInfo);
		int count = 1;
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;
		List<BookInfo> bookList = new ArrayList<>();
		userInfo = getBookInfo.getUserInfo();

		String bookInfoQuery = queryBuilder(getBookInfo, userInfo.getLang().getLangType());

		DBBean dbBean = (DBBean) ContextListener.getApplicationContext().getAttribute("db");
		dbConnection = DBConnection.getDBInstance(dbBean);
		connection = dbConnection.createConnection(dbBean);
		try {
			preparedStmt = connection.prepareStatement(bookInfoQuery);
			if (isTypeSet) {
				preparedStmt.setInt(count, Integer.parseInt(getBookInfo.getType()));
				++count;
				isTypeSet = false;
			}
			if (isTitleSet) {
				preparedStmt.setString(count, getBookInfo.getTitle());
				++count;
				isTitleSet = false;
			}
			if (isAuthorSet) {
				preparedStmt.setString(count, getBookInfo.getAuthor());
				isAuthorSet = false;
			}
			resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				author = new BookAuthor(resultSet.getString(AUTHOR_NAME));
				type = new BookType(resultSet.getString(TYPE_NAME));
				bookInfo = new BookInfo(resultSet.getInt(BOOK_INFO_ID), resultSet.getString(BOOK_INFO_TITLE),
						resultSet.getString(BOOK_INFO_DESC), resultSet.getInt(BOOK_INFO_PUBLISH_YEAR), author, type);
				bookList.add(bookInfo);
			}

		} catch (SQLException exception) {
			LOGGER.error("SQL Exception", exception);
			throw new DaoException(exception);
		} finally {
			dbConnection.closeResultSet(resultSet);
			dbConnection.closePreparedStatement(preparedStmt);
			dbConnection.closeConnection(connection);
		}
		return bookList;
	}

	private String queryBuilder(GetBookInfo bookInfo, String langType) {
		String book_info = "book_info_" + langType;
		String book_info_author = "book_info_author_" + langType;
		String book_author = "book_author_" + langType;
		String book_info_type = "book_info_type_" + langType;
		String book_type = "book_type_" + langType;

		StringBuffer query = new StringBuffer();
		query.append(
				"select distinct book_info.bi_id, book_info.bi_title, book_info.bi_desc, book_info.bi_publish_year, book_type.bt_name as type_name, book_author.ba_name as author_name "
						+ "from " + book_info + " as book_info " + "join " + book_info_author + " as info_author "
						+ "on book_info.bi_id = info_author.bi_id " + "join " + book_author + " as book_author "
						+ "on info_author.ba_id = book_author.ba_id " + "join " + book_info_type + " as info_type "
						+ "on info_type.bi_id = book_info.bi_id " + "join " + book_type + " as book_type "
						+ "on book_type.bt_id = info_type.bt_id ");
		String conditions = getWhereConditionsForQuery(bookInfo);
		query.append(conditions);
		LOGGER.info("query : " + query);
		return query.toString();
	}

	private String getWhereConditionsForQuery(GetBookInfo bookInfo) {
		boolean flag = false;
		StringBuffer whereCondition = new StringBuffer();
		whereCondition.append("");
		if (bookInfo.getType() != null) {
			whereCondition.append("where book_type.bt_id = ? ");
			flag = true;
			isTypeSet = true;
		}
		if (bookInfo.getTitle() != null) {
			if (flag == true) {
				whereCondition.append(" and ");
			} else {
				whereCondition.append("where ");
			}
			whereCondition.append("book_info.bi_title = ? ");
			flag = true;
			isTitleSet = true;
		}
		if (bookInfo.getAuthor() != null) {
			if (flag == true) {
				whereCondition.append(" and ");
			} else {
				whereCondition.append("where ");
			}
			whereCondition.append("book_author.ba_name = ? ");
			isAuthorSet = true;
		}
		return whereCondition.toString();
	}
}
