package com.web.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.web.command.creator.CommandCreator;
import com.web.command.exception.CommandException;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HttpSession httpSession;
	private static final CommandCreator CREATOR = CommandCreator.getCommandCreator();
	private static final Logger LOGGER = Logger.getLogger(ControllerServlet.class);
	private static final String LANG_TYPE = "langType";
	private static final String PAGE = "page";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		getCommand(request, response);
		try {
			dispatcher = request.getRequestDispatcher((String) request.getAttribute(PAGE));
			dispatcher.forward(request, response);
		} catch (ServletException exception) {
			LOGGER.error("Servlet Exception", exception);
		} catch (IOException exception) {
			LOGGER.error("IO Exception", exception);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String loginPage = "/login.jsp";
		req.setAttribute(LANG_TYPE, req.getParameter(LANG_TYPE));
		if (req.getParameter("newpage") != null && req.getParameter("newpage").equals("signup")) {
			loginPage = "/signUp.jsp";
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher(loginPage);
		dispatcher.forward(req, resp);
	}

	private void getCommand(HttpServletRequest request, HttpServletResponse response) {
		try {
			CREATOR.getCommand(request.getParameter("submit")).execute(request, response);
		} catch (CommandException exception) {
			LOGGER.error("Command Exception", exception);
		}

	}

}
