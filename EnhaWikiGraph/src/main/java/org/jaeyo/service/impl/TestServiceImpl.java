package org.jaeyo.service.impl;

import org.jaeyo.service.TestService;

public class TestServiceImpl implements TestService{

	@Override
	public String getTestWord() {
		return "testword from service";
	}

} //class