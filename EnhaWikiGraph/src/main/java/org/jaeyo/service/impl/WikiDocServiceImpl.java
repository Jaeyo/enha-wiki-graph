package org.jaeyo.service.impl;

import javax.inject.Inject;

import org.jaeyo.model.WikiDoc;
import org.jaeyo.repo.WikiDocRepository;
import org.jaeyo.service.WikiDocService;
import org.jaeyo.util.InjectLogger;
import org.slf4j.Logger;

public class WikiDocServiceImpl implements WikiDocService{
	@InjectLogger private Logger logger;
	@Inject private WikiDocRepository wikiDocRepo;

	@Override
	public boolean save(WikiDoc wikiDoc) {
		return wikiDocRepo.save(wikiDoc);
	}

	@Override
	public boolean isExists(String title) {
		return wikiDocRepo.isExists(title);
	}

	@Override
	public WikiDoc load(String title) {
		return wikiDocRepo.load(title);
	}
} //class