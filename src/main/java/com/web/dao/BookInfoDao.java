package com.web.dao;

import java.util.List;
import com.web.dao.exception.DaoException;
import com.web.domain.BookInfo;
import com.web.dto.GetBookInfo;

public interface BookInfoDao {

	List<BookInfo> getBookInfo(GetBookInfo bookInfo) throws DaoException;

}
