package org.jaeyo.rdb;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;

public interface ConnectionPool {
	public Connection getConnection() throws SQLException;
	public JdbcTemplate getJdbcTemplate();
}