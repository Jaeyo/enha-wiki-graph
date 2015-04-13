package org.jaeyo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Startup extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(Startup.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		logger.trace("invoked");
	} // init
} // class