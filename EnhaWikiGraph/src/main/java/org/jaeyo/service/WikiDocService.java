package org.jaeyo.service;

import org.jaeyo.model.WikiDoc;

public interface WikiDocService {
	public boolean save(WikiDoc wikiDoc);
	public boolean isExists(String title);
	public WikiDoc load(String title);
}