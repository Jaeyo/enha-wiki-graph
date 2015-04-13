package org.jaeyo.repo.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.jaeyo.model.WikiDoc;
import org.jaeyo.model.WikiDocEdge;
import org.jaeyo.rdb.impl.H2ConnectionPool;
import org.jaeyo.util.ReflectionUtil;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class WikiDocEdgesRepositoryImplTest {
	private JdbcTemplate jdbcTemplate = new H2ConnectionPool().getJdbcTemplate();

	public WikiDocEdgesRepositoryImpl newWikiDocEdgesRepositoryImpl(){
		WikiDocEdgesRepositoryImpl repo = new WikiDocEdgesRepositoryImpl();
		ReflectionUtil.setPrivateField(repo, "conns", new H2ConnectionPool());
		ReflectionUtil.setPrivateField(repo, "logger", LoggerFactory.getLogger(WikiDocEdgesRepositoryImpl.class));
		return repo;
	} //newWikiDocEdgesRepositoryImpl
	
	public WikiDocRepositoryImpl newWikiDocRepositoryImpl(){
		WikiDocRepositoryImpl repo = new WikiDocRepositoryImpl();
		ReflectionUtil.setPrivateField(repo, "conns", new H2ConnectionPool());
		ReflectionUtil.setPrivateField(repo, "logger", LoggerFactory.getLogger(WikiDocEdgesRepositoryImpl.class));
		return repo;
	} //newWikiDocEdgesRepositoryImpl
	
	@Test
	public void test() {
		try{
			WikiDocEdgesRepositoryImpl edgesRepo = newWikiDocEdgesRepositoryImpl();
			WikiDocRepositoryImpl wikiRepo = newWikiDocRepositoryImpl();
			
			WikiDoc wikiDoc1 = new WikiDoc("wiki1", "test", "test");
			WikiDoc wikiDoc2 = new WikiDoc("wiki2", "test", "test");
			WikiDoc wikiDoc3 = new WikiDoc("wiki3", "test", "test");
			
			assertTrue(wikiRepo.save(wikiDoc1));
			assertTrue(wikiRepo.save(wikiDoc2));
			assertTrue(wikiRepo.save(wikiDoc3));
			
			WikiDocEdge edge1to3 = new WikiDocEdge(wikiDoc1.getTitle(), wikiDoc3.getTitle());
			WikiDocEdge edge2to3 = new WikiDocEdge(wikiDoc2.getTitle(), wikiDoc3.getTitle());
			WikiDocEdge edge3to1 = new WikiDocEdge(wikiDoc3.getTitle(), wikiDoc1.getTitle());
			
			assertTrue(edgesRepo.save(edge1to3));
			assertTrue(edgesRepo.save(edge2to3));
			assertTrue(edgesRepo.save(edge3to1));
			
			List<WikiDocEdge> wikiDocEdges1to3and2to3 = edgesRepo.loadByDest(wikiDoc3.getTitle());
			assertTrue(wikiDocEdges1to3and2to3.size()==2);
			if(wikiDocEdges1to3and2to3.get(0).getSrcWikiDocTitle().equals(wikiDoc2.getTitle()))
				assertTrue(wikiDocEdges1to3and2to3.get(1).getSrcWikiDocTitle().equals(wikiDoc1.getTitle()));
			else
				assertTrue(wikiDocEdges1to3and2to3.get(1).getSrcWikiDocTitle().equals(wikiDoc2.getTitle()));
			
			List<WikiDocEdge> wikiDocEdges3to1 = edgesRepo.loadByDest(wikiDoc1.getTitle());
			assertTrue(wikiDocEdges3to1.size()==1);
			assertTrue(wikiDocEdges3to1.get(0).getSrcWikiDocTitle().equals(wikiDoc3.getTitle()));
			
			assertTrue(edgesRepo.delete(wikiDocEdges3to1.get(0)));
			wikiDocEdges3to1 = edgesRepo.loadByDest(wikiDoc1.getTitle());
			assertTrue(wikiDocEdges3to1.size()==0);
			wikiDocEdges1to3and2to3 = edgesRepo.loadByDest(wikiDoc3.getTitle());
			assertTrue(wikiDocEdges1to3and2to3.size()==2);
			
			assertTrue(edgesRepo.deleteBySrc(wikiDoc1.getTitle()));
			List<WikiDocEdge> wikiDocEdges2 = edgesRepo.loadByDest(wikiDoc3.getTitle());
			assertTrue(wikiDocEdges2.size()==1);
			assertTrue(wikiDocEdges2.get(0).getSrcWikiDocTitle().equals(wikiDoc2.getTitle()));
		} catch(Exception e){
			e.printStackTrace();
			fail();
		} finally{
			jdbcTemplate.update("delete from wiki_doc");
			jdbcTemplate.update("delete from wiki_doc_edges");
		} //finally
	} //test
} //class