package com.web.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;
import com.web.service.PasswordHashing;
import com.web.service.exception.ServiceException;

public class PasswordHashingServiceImpl implements PasswordHashing {

	private static final Logger LOGGER = Logger.getLogger(PasswordHashingServiceImpl.class);

	@Override
	public String generateHash(String pwd) throws ServiceException {
		StringBuilder hash = new StringBuilder();
		MessageDigest sha;
		try {
			sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(pwd.getBytes());
			char digits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException exception) {
			LOGGER.error("NoSuchAlgorithm Exception", exception);
			throw new ServiceException(exception);
		}

		return hash.toString();
	}

}
