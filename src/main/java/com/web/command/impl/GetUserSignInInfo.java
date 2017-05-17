package com.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.web.command.Command;
import com.web.command.exception.CommandException;
import com.web.domain.UserInfo;
import com.web.dto.SignInInfo;
import com.web.service.SignInInfoService;
import com.web.service.exception.ServiceException;
import com.web.service.factory.ServiceFactory;

public class GetUserSignInInfo implements Command {

	private static SignInInfo signInInfo;
	private static ServiceFactory serviceFactoryInstance;
	private static SignInInfoService signInInfoService;
	private static UserInfo userInfo;
	private static HttpSession httpSession;
	private static final Logger LOGGER = Logger.getLogger(GetUserSignInInfo.class);
	private static final String USER_ERROR = "error";
	private static final String USERID_ERROR = "Wrong UserId!!";
	private static final String PASSWORD_ERROR = "Wrong Password for entered UserId!!";
	private static final String VALID_USER = "validUser";
	private static boolean IS_VALID_USER = false;
	private static final String USER_HOME_PAGE = "WEB-INF/jsp/userHome.jsp";
	private static final String ADMINISTRATOR_HOME_PAGE = "WEB-INF/jsp/administratorHome.jsp";
	private static final String UNAME = "uName";
	private static final String PAGE = "page";
	private static final String LOGIN_PAGE = "/login.jsp";
	private static final String LANG_TYPE = "langType";
	private static final String USER_ID = "uId";
	private static final String PASSWORD = "pwd";
	private static final String USER = "user";
	private static final String USER_INFO = "userInfo";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		boolean validUser;
		signInInfo = new SignInInfo(Integer.parseInt(request.getParameter(USER_ID)), request.getParameter(PASSWORD));
		serviceFactoryInstance = ServiceFactory.getServiceFactoryInstance();
		signInInfoService = serviceFactoryInstance.getSignInInfoServiceInstance();
		try {
			validUser = signInInfoService.checksIfValidUser(signInInfo);

			request.setAttribute(PAGE, LOGIN_PAGE);
			if (validUser != false) {
				userInfo = signInInfoService.getUserInfoById(signInInfo);
				validUser = signInInfoService.checkIfPasswordMatches(signInInfo, userInfo.getPassword());
				if (validUser == false) {
					LOGGER.info("invalid user having wrong password");
					request.setAttribute(USER_ERROR, PASSWORD_ERROR);
				} else {
					IS_VALID_USER = true;
					httpSession = request.getSession();
					httpSession.setAttribute(UNAME, userInfo.getUserName());
					httpSession.setAttribute(USER_INFO, userInfo);

					if (userInfo.getUserRole().equalsIgnoreCase(USER)) {
						request.setAttribute(PAGE, USER_HOME_PAGE);
					} else {
						request.setAttribute(PAGE, ADMINISTRATOR_HOME_PAGE);
					}
					String langType = userInfo.getLang().getLangType() + "_" + userInfo.getLang().getCountry();
					httpSession.setAttribute(LANG_TYPE, langType);
					LOGGER.info(langType);
				}
			} else {
				LOGGER.info("invalid user having wrong userId");
				request.setAttribute(USER_ERROR, USERID_ERROR);
			}
			request.setAttribute(VALID_USER, IS_VALID_USER);
		} catch (ServiceException exception) {
			LOGGER.error("Service Exception");
			throw new CommandException(exception);
		}
	}

}
