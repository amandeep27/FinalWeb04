package com.web.service;

import com.web.service.exception.ServiceException;

public interface PasswordHashing {

	String generateHash(String pwd) throws ServiceException;
}
