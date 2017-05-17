package com.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.web.command.Command;
import com.web.command.exception.CommandException;

public class LogOut implements Command {

	private static HttpSession session;
	private static final Logger LOGGER = Logger.getLogger(LogOut.class);
	private static final String PAGE = "page";
	private static final String LOGIN_PAGE = "/login.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		session = request.getSession(false);
		request.removeAttribute("validUser");
		if (session != null) {
			session.invalidate();
		}
		LOGGER.info("LOGGED OUT!!");
		request.setAttribute(PAGE, LOGIN_PAGE);
	}

}
