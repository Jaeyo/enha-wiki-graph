package org.jaeyo.repo.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.jaeyo.model.UserView;
import org.jaeyo.model.WikiDoc;
import org.jaeyo.rdb.ConnectionPool;
import org.jaeyo.repo.UserViewRepository;
import org.jaeyo.util.InjectLogger;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class UserViewRepositoryImpl implements UserViewRepository{
	@InjectLogger private Logger logger;
	@Inject private ConnectionPool conns;

	@Override
	public boolean save(UserView userView) {
		try {
			conns.getJdbcTemplate().update("insert into user_view (session_id, last_updated, wiki_doc_title) values(?,?,?)", 
					userView.getSessionId(), userView.getLastUpdated(), userView.getWikiDocTitle());
			return true;
		} catch (DataAccessException e) {
			logger.error(String.format("%s, errmsg : %s", e.getClass().getSimpleName(), e.getMessage()));
			return false;
		} //catch
	} //save

	@Override
	public List<WikiDoc> loadBySessionId(String sessionId) {
		SqlRowSet rowSet = conns.getJdbcTemplate().queryForRowSet("select w.title, w.content_html, w.content_hash "
				+ "from wiki_doc w, user_view u where w.title = u.wiki_doc_title and u.session_id = ?", sessionId);
		List<WikiDoc> wikiDocs = new ArrayList<WikiDoc>();
		while(rowSet.next()){
			try {
				String title = rowSet.getString("title");

				SerialClob contentHtmlClob = (SerialClob) rowSet.getObject("content_html");
				BufferedReader reader = new BufferedReader(contentHtmlClob.getCharacterStream());
				String line = null;
				StringBuilder contentHtmlSb = new StringBuilder();
				while((line = reader.readLine())!=null)
					contentHtmlSb.append(line);

				String contentHash = rowSet.getString("content_hash");
				wikiDocs.add(new WikiDoc(title, contentHtmlSb.toString(), contentHash));
			} catch (IOException | SerialException e) {
				logger.error(String.format("%s, errmsg : %s", e.getClass().getSimpleName(), e.getMessage()), e);
			} //catch
		} //while
		return wikiDocs;
	} //loadBySessionId
} //class