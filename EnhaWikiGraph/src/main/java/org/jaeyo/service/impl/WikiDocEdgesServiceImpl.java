package org.jaeyo.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.jaeyo.model.WikiDoc;
import org.jaeyo.model.WikiDocEdge;
import org.jaeyo.repo.WikiDocEdgesRepository;
import org.jaeyo.service.WikiDocEdgesService;
import org.jaeyo.util.InjectLogger;
import org.slf4j.Logger;

public class WikiDocEdgesServiceImpl implements WikiDocEdgesService{
	@InjectLogger private Logger logger;
	@Inject private WikiDocEdgesRepository wikiDocEdgesRepo;

	@Override
	public boolean save(WikiDoc srcWikiDoc, WikiDoc destWikiDoc) {
		return wikiDocEdgesRepo.save(new WikiDocEdge(srcWikiDoc.getTitle(), destWikiDoc.getTitle()));
	} //save

	@Override
	public boolean delete(WikiDocEdge edge) {
		return wikiDocEdgesRepo.delete(edge);
	} //delete

	@Override
	public boolean deleteBySrc(String srcWikiDocTitle) {
		return wikiDocEdgesRepo.deleteBySrc(srcWikiDocTitle);
	} //deleteBySrc

	@Override
	public List<WikiDocEdge> loadBySrc(WikiDoc srcWikidoc) {
		return wikiDocEdgesRepo.loadBySrc(srcWikidoc.getTitle());
	} //loadBySrc

	@Override
	public List<WikiDocEdge> loadByDest(WikiDoc destWikiDoc) {
		return wikiDocEdgesRepo.loadByDest(destWikiDoc.getTitle());
	} //loadByDest
} //class