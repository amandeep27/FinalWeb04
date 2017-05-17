package com.web.service.factory;

import com.web.service.BookInfoService;
import com.web.service.ChangeLanguageService;
import com.web.service.SignInInfoService;
import com.web.service.SignUpInfoService;
import com.web.service.impl.BookInfoServiceImpl;
import com.web.service.impl.ChangeLanguageServiceImpl;
import com.web.service.impl.SignInInfoServiceImpl;
import com.web.service.impl.SignUpInfoServiceImpl;

public class ServiceFactory {

	private static volatile ServiceFactory serviceFactoryInstance;

	private ServiceFactory() {

	}

	public static ServiceFactory getServiceFactoryInstance() {
		if (serviceFactoryInstance == null) {
			synchronized (ServiceFactory.class) {
				if (serviceFactoryInstance == null) {
					serviceFactoryInstance = new ServiceFactory();
				}
			}
		}
		return serviceFactoryInstance;
	}

	public SignUpInfoService getSignUpInfoServiceInstance() {
		return new SignUpInfoServiceImpl();
	}

	public SignInInfoService getSignInInfoServiceInstance() {
		return new SignInInfoServiceImpl();
	}

	public BookInfoService getBookInfoServiceInstance() {
		return new BookInfoServiceImpl();
	}

	public ChangeLanguageService changeLanguageServiceInstance() {
		return new ChangeLanguageServiceImpl();
	}
}
