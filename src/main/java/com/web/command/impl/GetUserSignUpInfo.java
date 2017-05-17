package com.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.web.command.Command;
import com.web.command.exception.CommandException;
import com.web.dto.SignUpInfo;
import com.web.service.SignUpInfoService;
import com.web.service.exception.ServiceException;
import com.web.service.factory.ServiceFactory;

public class GetUserSignUpInfo implements Command {

	private static final Logger LOGGER = Logger.getLogger(GetUserSignUpInfo.class);
	private static SignUpInfo signUp;
	private static ServiceFactory serviceFactoryInstance;
	private static SignUpInfoService signUpInfoService;
	private static final String PAGE = "page";
	private static final String LOGIN_PAGE = "/login.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		signUp = setSignUpInfo(request);
		serviceFactoryInstance = ServiceFactory.getServiceFactoryInstance();
		signUpInfoService = serviceFactoryInstance.getSignUpInfoServiceInstance();
		try {
			request.setAttribute(PAGE, LOGIN_PAGE);
			signUpInfoService.saveInfoInDB(signUp);
		} catch (ServiceException exception) {
			LOGGER.error("Service Exception");
			throw new CommandException(exception);
		}
	}

	private SignUpInfo setSignUpInfo(HttpServletRequest request) {
		signUp = new SignUpInfo();
		signUp.setUserId(Integer.parseInt(request.getParameter("uId")));
		signUp.setUserName(request.getParameter("uName"));
		signUp.setUserRole(request.getParameter("role"));
		signUp.setPassword(request.getParameter("pwd"));
		signUp.setRepeatPassword(request.getParameter("pwd-repeat"));
		signUp.setLanguage(request.getParameter("lang"));
		return signUp;
	}

}
