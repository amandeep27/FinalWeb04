package com.web.service.impl.validator;

import com.web.service.exception.ServiceException;

public class SignUpInfoValidator {

	private static final String WRONG_PASSWORD = "Eneterd Passwords doesn't match";
	private static volatile SignUpInfoValidator validator;

	private SignUpInfoValidator() {

	}

	public static SignUpInfoValidator getSignUpInfoValidator() {
		if (validator == null) {
			synchronized (SignUpInfoValidator.class) {
				if (validator == null) {
					validator = new SignUpInfoValidator();
				}
			}
		}
		return validator;
	}
	
	public void checkBothPasswords(String pwd, String repeatPwd) throws ServiceException{
		if(!pwd.equals(repeatPwd)){
			throw new ServiceException(WRONG_PASSWORD);
		}
	}
}
