package com.web.service.impl;

import java.util.List;
import org.apache.log4j.Logger;
import com.web.dao.BookInfoDao;
import com.web.dao.exception.DaoException;
import com.web.dao.factory.DaoFactory;
import com.web.domain.BookInfo;
import com.web.dto.GetBookInfo;
import com.web.service.BookInfoService;
import com.web.service.exception.ServiceException;

public class BookInfoServiceImpl implements BookInfoService {

	private static final Logger LOGGER = Logger.getLogger(BookInfoServiceImpl.class);

	private static DaoFactory daoFactoryInstance;
	private static BookInfoDao bookInfoDao;

	@Override
	public List<BookInfo> getBookInfo(GetBookInfo bookInfo) throws ServiceException {
		List<BookInfo> bookList = null;

		try {
			daoFactoryInstance = DaoFactory.getDaoFactoryInstance();
			bookInfoDao = daoFactoryInstance.getBookInfoDaoInstance();
			bookInfo.setType(getBookType(bookInfo.getType()));

			bookList = bookInfoDao.getBookInfo(bookInfo);
		} catch (DaoException exception) {
			LOGGER.error("DAO Exception");
			throw new ServiceException(exception);
		}

		return bookList;
	}

	private String getBookType(String type) {
		if (type.equals("all")) {
			type = null;
		} else if (type.equals("e-book")) {
			type = "2";
		} else {
			type = "1";
		}
		return type;
	}

}
