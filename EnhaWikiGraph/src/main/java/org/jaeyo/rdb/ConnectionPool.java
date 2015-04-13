package org.jaeyo.rdb;

import java.sql.Connection;

public interface ConnectionPool {
	public Connection getConnection();
}