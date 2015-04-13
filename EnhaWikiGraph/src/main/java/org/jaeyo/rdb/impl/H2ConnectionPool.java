package org.jaeyo.rdb.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.h2.jdbcx.JdbcDataSource;
import org.jaeyo.rdb.ConnectionPool;
import org.springframework.jdbc.core.JdbcTemplate;

public class H2ConnectionPool implements ConnectionPool{
	private static JdbcDataSource dataSource = null;
	private JdbcTemplate jdbcTemplate = null;
	
	public H2ConnectionPool() {
		synchronized (H2ConnectionPool.class) {
			if(dataSource == null){
				dataSource = new JdbcDataSource();
//				dataSource.setUrl("jdbc:h2:~/test");
				dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
				dataSource.setUser("sa");
				dataSource.setPassword("sa");
				initJdbcTemaplte();
				createTables();
			} else{
				initJdbcTemaplte();
			} //if
		} //sync
	} //INIT
	
	private void initJdbcTemaplte(){
		jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
	} //initJdbcTemplate
	
	private void createTables(){
		jdbcTemplate.batchUpdate(new String[]{
			"create table wiki_doc (" + 
			"title varchar(100) primary key, " +
			"content_html text, " +
			"content_hash varchar(50) )",
			
			"create table user_view (" + 
			"session_id varchar(100), " +
			"last_updated datetime, " +
			"wiki_doc_title varchar(100))"
		});
	} //createTables

	@Override
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}
} //class