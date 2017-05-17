package com.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.web.command.Command;
import com.web.command.exception.CommandException;
import com.web.domain.UserInfo;
import com.web.service.ChangeLanguageService;
import com.web.service.exception.ServiceException;
import com.web.service.factory.ServiceFactory;

public class ChangeUserLanguage implements Command {

	private static final String USER_INFO = "userInfo";
	private static final Logger LOGGER = Logger.getLogger(ChangeUserLanguage.class);
	private static ServiceFactory serviceFactoryInstance;
	private static ChangeLanguageService changeLanguage;
	private static UserInfo userInfo;
	private static HttpSession httpSession;

	private static final String USER_HOME_PAGE = "WEB-INF/jsp/userHome.jsp";
	private static final String ADMINISTRATOR_HOME_PAGE = "WEB-INF/jsp/administratorHome.jsp";
	private static final String PAGE = "page";
	private static final String USER = "user";
	private static final String LANG_TYPE = "langType";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		serviceFactoryInstance = ServiceFactory.getServiceFactoryInstance();
		changeLanguage = serviceFactoryInstance.changeLanguageServiceInstance();

		httpSession = request.getSession();
		userInfo = (UserInfo) httpSession.getAttribute(USER_INFO);
		try {
			userInfo = changeLanguage.changeUserLanguage(userInfo, request.getParameter("lang"));
			if (userInfo.getUserRole().equalsIgnoreCase(USER)) {
				request.setAttribute(PAGE, USER_HOME_PAGE);
			} else {
				request.setAttribute(PAGE, ADMINISTRATOR_HOME_PAGE);
			}
			String langType = userInfo.getLang().getLangType() + "_" + userInfo.getLang().getCountry();
			httpSession.setAttribute(LANG_TYPE, langType);
		} catch (ServiceException exception) {
			LOGGER.info("Service Exception");
			throw new CommandException(exception);
		}
	}

}
