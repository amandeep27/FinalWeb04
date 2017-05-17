package com.web.listener;

import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.PropertyConfigurator;
import com.web.domain.DBBean;

public class ContextListener implements ServletContextListener {

	private static ServletContext context;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		context = null;
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
		initailizeLogger(context);
		initializeDBObject(context);
		getHashMagicLine(context);
	}

	private void initailizeLogger(ServletContext context) {
		String log4jConfigFile = context.getInitParameter("log4j-config-location");
		String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
		PropertyConfigurator.configure(fullPath);
	}

	private void initializeDBObject(ServletContext context) {
		String driver = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String userName = context.getInitParameter("username");
		String password = context.getInitParameter("password");
		DBBean dbBean = new DBBean(driver, url, userName, password);
		context.setAttribute("db", dbBean);
	}

	private void getHashMagicLine(ServletContext context) {
		String magicLine = context.getInitParameter("hashLine");
		context.setAttribute("magic", magicLine);
	}

	public static ServletContext getApplicationContext() {
		return context;
	}

}
