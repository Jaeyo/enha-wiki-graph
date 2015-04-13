package org.jaeyo.repo.impl;

import static org.junit.Assert.*;

import java.awt.image.ReplicateScaleFilter;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jaeyo.model.WikiDoc;
import org.jaeyo.rdb.impl.H2ConnectionPool;
import org.jaeyo.util.ReflectionUtil;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class WikiDocRepositoryImplTest {
	private JdbcTemplate jdbcTemplate = new H2ConnectionPool().getJdbcTemplate();

	private WikiDocRepositoryImpl newWikiDocRepositoryImpl(){
		WikiDocRepositoryImpl repo = new WikiDocRepositoryImpl();
		ReflectionUtil.setPrivateField(repo, "conns", new H2ConnectionPool());
		ReflectionUtil.setPrivateField(repo, "logger", LoggerFactory.getLogger(WikiDocRepositoryImpl.class));	
		return repo;
	} //newWikiDocrepositoryImpl
	
	@Test
	public void test_save() {
		try {
			WikiDocRepositoryImpl repo = newWikiDocRepositoryImpl();
			
			WikiDoc wikiDoc = new WikiDoc("test_title", "test_content", "test_hash");
			assertFalse(repo.isExists(wikiDoc.getTitle()));
			assertTrue(repo.save(wikiDoc));
			assertTrue(repo.isExists(wikiDoc.getTitle()));
			assertTrue(repo.load(wikiDoc.getTitle()).getContentHtml().equals("test_content"));
			
			wikiDoc.setContentHtml("changed_content");
			assertTrue(repo.update(wikiDoc));
			assertTrue(repo.load(wikiDoc.getTitle()).getContentHtml().equals("changed_content"));
			assertTrue(repo.delete(wikiDoc));
			assertFalse(repo.isExists(wikiDoc.getTitle()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally{
			jdbcTemplate.update("delete from wiki_doc");
		} //finally
	} //test_save
} //class