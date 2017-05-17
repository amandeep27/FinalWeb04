package com.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.command.exception.CommandException;

public interface Command {

	void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
