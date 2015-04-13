package org.jaeyo.service;

import java.util.List;

import org.jaeyo.model.WikiDoc;
import org.jaeyo.model.WikiDocEdge;

public interface WikiDocEdgesService {
	public boolean save(WikiDoc srcWikiDoc, WikiDoc destWikiDoc);
	public boolean delete(WikiDocEdge edge);
	public boolean deleteBySrc(String srcWikiDocTitle);
	public List<WikiDocEdge> loadBySrc(WikiDoc srcWikidoc);
	public List<WikiDocEdge> loadByDest(WikiDoc destWikiDoc);
}