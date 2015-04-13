package org.jaeyo.repo;

import java.util.List;

import org.jaeyo.model.WikiDocEdge;

public interface WikiDocEdgesRepository {
	public boolean save(WikiDocEdge edge);
	public boolean delete(WikiDocEdge edge);
	public boolean deleteBySrc(String srcWikiDocTitle);
	public List<WikiDocEdge> loadBySrc(String srcWikiDocTitle);
	public List<WikiDocEdge> loadByDest(String destWikiDocTitle);
}