package org.jaeyo.repo.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.jaeyo.model.UserView;
import org.jaeyo.model.WikiDoc;
import org.jaeyo.rdb.impl.H2ConnectionPool;
import org.jaeyo.util.ReflectionUtil;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserViewRepositoryImplTest {
	private JdbcTemplate jdbcTemplate = new H2ConnectionPool().getJdbcTemplate();

	private UserViewRepositoryImpl newUserViewRepositoryImpl(){
		UserViewRepositoryImpl repo = new UserViewRepositoryImpl();
		ReflectionUtil.setPrivateField(repo, "conns", new H2ConnectionPool());
		ReflectionUtil.setPrivateField(repo, "logger", LoggerFactory.getLogger(UserViewRepositoryImpl.class));
		return repo;
	} //newUserViewRepositoryImpl
	
	private WikiDocRepositoryImpl newWikiDocRepositoryImpl (){
		WikiDocRepositoryImpl repo = new WikiDocRepositoryImpl();
		ReflectionUtil.setPrivateField(repo, "conns", new H2ConnectionPool());
		ReflectionUtil.setPrivateField(repo, "logger", LoggerFactory.getLogger(UserViewRepositoryImpl.class));
		return repo;
	} //newWikiDocRepositoryImpl 
	
	@Test
	public void test() {
		try{
			UserViewRepositoryImpl userViewRepo = newUserViewRepositoryImpl();
			WikiDocRepositoryImpl wikiDocRepo = newWikiDocRepositoryImpl();
			
			WikiDoc wikiDoc = new WikiDoc("test_title", "test_content", "test_hash");
			assertTrue(wikiDocRepo.save(wikiDoc));
			
			UserView userView = new UserView(UUID.randomUUID().toString(), new Date(), wikiDoc.getTitle());
			assertTrue(userViewRepo.save(userView));
			List<WikiDoc> loadedWikiDocs = userViewRepo.loadBySessionId(userView.getSessionId());
			assertTrue(loadedWikiDocs.size() == 1);
			assertTrue(loadedWikiDocs.get(0).getTitle().equals(wikiDoc.getTitle()));
			assertTrue(loadedWikiDocs.get(0).getContentHtml().equals(wikiDoc.getContentHtml()));
			assertTrue(loadedWikiDocs.get(0).getContentHash().equals(wikiDoc.getContentHash()));
		} catch(Exception e){
			e.printStackTrace();
			fail();
		} finally{
			jdbcTemplate.batchUpdate(new String[]{"delete from wiki_doc", "delete from user_view"});
		} //finally
	}
} //class