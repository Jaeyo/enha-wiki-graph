package org.jaeyo.repo;

import org.jaeyo.model.WikiDoc;

public interface WikiDocRepository {
	public boolean save(WikiDoc wikiDoc);
	public boolean update(WikiDoc wikiDoc);
	public boolean delete(WikiDoc wikiDoc);
	public boolean isExists(String title);
	public WikiDoc load(String title);
} //interface