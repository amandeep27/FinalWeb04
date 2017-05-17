package com.web.command.creator;

import java.util.HashMap;
import java.util.Map;
import com.web.command.Command;
import com.web.command.impl.ChangeUserLanguage;
import com.web.command.impl.GetBook;
import com.web.command.impl.GetUserSignInInfo;
import com.web.command.impl.GetUserSignUpInfo;
import com.web.command.impl.LogOut;

public class CommandCreator {

	private static volatile CommandCreator creator;
	private static final String GET_SIGN_IN_INFO = "SignIn";
	private static final String GET_SIGN_UP_INFO = "SignUp";
	private static final String GET_BOOK = "getBook";
	private static final String CHANGE_LANGUAGE = "changeLanguage";

	private static final String LOG_OUT = "logOut";
	private Map<String, Command> commandCreator = new HashMap<>();

	private CommandCreator() {
		commandCreator.put(GET_SIGN_IN_INFO, new GetUserSignInInfo());
		commandCreator.put(GET_SIGN_UP_INFO, new GetUserSignUpInfo());
		commandCreator.put(LOG_OUT, new LogOut());

		commandCreator.put(GET_BOOK, new GetBook());
		commandCreator.put(CHANGE_LANGUAGE, new ChangeUserLanguage());
	}

	public static CommandCreator getCommandCreator() {
		if (creator == null) {
			synchronized (CommandCreator.class) {
				if (creator == null) {
					creator = new CommandCreator();
				}
			}
		}
		return creator;
	}

	public Command getCommand(String command) {
		return commandCreator.get(command);
	}
}
