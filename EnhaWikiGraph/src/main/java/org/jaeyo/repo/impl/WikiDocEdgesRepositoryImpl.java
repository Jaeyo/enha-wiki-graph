package org.jaeyo.repo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jaeyo.model.WikiDocEdge;
import org.jaeyo.rdb.ConnectionPool;
import org.jaeyo.repo.WikiDocEdgesRepository;
import org.jaeyo.util.InjectLogger;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class WikiDocEdgesRepositoryImpl implements WikiDocEdgesRepository{
	@InjectLogger private Logger logger;
	@Inject private ConnectionPool conns;

	@Override
	public boolean save(WikiDocEdge edge) {
		try {
			conns.getJdbcTemplate().update("insert into wiki_doc_edges(src_wiki_doc_title, dest_wiki_doc_title) values(?,?)",
					edge.getSrcWikiDocTitle(), edge.getDestWikiDocTitle());
			return true;
		} catch (DataAccessException e) {
			logger.error(String.format("%s, errmsg : %s", e.getClass().getSimpleName(), e.getMessage()), e);
			return false;
		} //catch
	} //save

	@Override
	public boolean delete(WikiDocEdge edge) {
		try {
			conns.getJdbcTemplate().update("delete from wiki_doc_edges where src_wiki_doc_title = ? and dest_wiki_doc_title = ?",
					edge.getSrcWikiDocTitle(), edge.getDestWikiDocTitle());
			return true;
		} catch (DataAccessException e) {
			logger.error(String.format("%s, errmsg : %s", e.getClass().getSimpleName(), e.getMessage()), e);
			return false;
		} //catch
	} //delete

	@Override
	public boolean deleteBySrc(String srcWikiDocTitle) {
		try {
			conns.getJdbcTemplate().update("delete from wiki_doc_edges where src_wiki_doc_title = ?", srcWikiDocTitle);
			return true;
		} catch (DataAccessException e) {
			logger.error(String.format("%s, errmsg : %s", e.getClass().getSimpleName(), e.getMessage()), e);
			return false;
		} //catch
	} //deleteBySrc

	@Override
	public List<WikiDocEdge> loadBySrc(String srcWikiDocTitle) {
		try {
			SqlRowSet rowSet = conns.getJdbcTemplate().queryForRowSet("select src_wiki_doc_title, dest_wiki_doc_title "
					+ "from wiki_doc_edges where src_wiki_doc_title = ?", srcWikiDocTitle);
			
			List<WikiDocEdge> edges = new ArrayList<WikiDocEdge>();
			while(rowSet.next())
				edges.add(new WikiDocEdge(rowSet.getString("src_wiki_doc_title"), rowSet.getString("dest_wiki_doc_title")));
			return edges;
		} catch (DataAccessException e) {
			logger.error(String.format("%s, errmsg : %s", e.getClass().getSimpleName(), e.getMessage()), e);
			return null;
		} //catch
	} //loadBySrc

	@Override
	public List<WikiDocEdge> loadByDest(String destWikiDocTitle) {
		try {
			SqlRowSet rowSet = conns.getJdbcTemplate().queryForRowSet("select src_wiki_doc_title, dest_wiki_doc_title "
					+ "from wiki_doc_edges where dest_wiki_doc_title = ?", destWikiDocTitle);
			
			List<WikiDocEdge> edges = new ArrayList<WikiDocEdge>();
			while(rowSet.next())
				edges.add(new WikiDocEdge(rowSet.getString("src_wiki_doc_title"), rowSet.getString("dest_wiki_doc_title")));
			return edges;
		} catch (DataAccessException e) {
			logger.error(String.format("%s, errmsg : %s", e.getClass().getSimpleName(), e.getMessage()), e);
			return null;
		} //catch	
	} //loadByDest
} //class