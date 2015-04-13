package org.jaeyo.repo.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.jaeyo.model.WikiDoc;
import org.jaeyo.rdb.ConnectionPool;
import org.jaeyo.repo.WikiDocRepository;
import org.jaeyo.util.InjectLogger;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

public class WikiDocRepositoryImpl implements WikiDocRepository{
	@Inject private ConnectionPool conns;
	@InjectLogger private Logger logger;

	@Override
	public boolean save(WikiDoc wikiDoc) {
		try {
			logger.info("test");
			conns.getJdbcTemplate().update("insert into wiki_doc (title, content_html, content_hash) values(?,?,?)", 
					wikiDoc.getTitle(), wikiDoc.getContentHtml(), wikiDoc.getContentHash());
			return true;
		} catch (DataAccessException e) {
			logger.error(String.format("%s, errmsg : %s", e.getClass().getSimpleName(), e.getMessage()), e);
			return false;
		} //catch
	} //save

	@Override
	public boolean update(WikiDoc wikiDoc) {
		try {
			conns.getJdbcTemplate().update("update wiki_doc set content_html=?, content_hash=? where title=?", 
					wikiDoc.getContentHtml(), wikiDoc.getContentHash(), wikiDoc.getTitle());
			return true;
		} catch (DataAccessException e) {
			logger.error(String.format("%s, errmsg : %s", e.getClass().getSimpleName(), e.getMessage()), e);
			return false;
		} //catch
	} //update

	@Override
	public boolean delete(WikiDoc wikiDoc) {
		try {
			conns.getJdbcTemplate().update("delete wiki_doc where title=?", wikiDoc.getTitle());
			return true;
		} catch (DataAccessException e) {
			logger.error(String.format("%s, errmsg : %s", e.getClass().getSimpleName(), e.getMessage()), e);
			return false;
		} //catch
	} //delete

	@Override
	public boolean isExists(String title) {
		try {
			int count = conns.getJdbcTemplate().queryForObject("select count(*) from wiki_doc where title = ?", Integer.class, title);
			return count == 1;
		} catch (DataAccessException e) {
			logger.error(String.format("%s, errmsg : %s", e.getClass().getSimpleName(), e.getMessage()), e);
			return false;
		} //catch
	} //boolean

	@Override
	public WikiDoc load(String title) {
		return conns.getJdbcTemplate().queryForObject("select title, content_html, content_hash from wiki_doc where title = ?", new String[]{title}, new RowMapper<WikiDoc>() {
			@Override
			public WikiDoc mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new WikiDoc(rs.getString("title"), rs.getString("content_html"), rs.getString("content_hash"));
			} //mapRow
		});
	} //load
} //class