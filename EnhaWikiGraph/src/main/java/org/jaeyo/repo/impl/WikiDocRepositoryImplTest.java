package org.jaeyo.repo.impl;

import java.util.Random;
import java.util.UUID;

import org.jaeyo.model.WikiDoc;
import org.jaeyo.repo.WikiDocRepository;

public class WikiDocRepositoryImplTest implements WikiDocRepository{

	@Override
	public boolean save(WikiDoc wikiDoc) {
		return new Random().nextBoolean();
	}

	@Override
	public boolean isExists(String title) {
		return new Random().nextBoolean();
	}

	@Override
	public WikiDoc load(String title) {
		String contentHtml = UUID.randomUUID().toString();
		return new WikiDoc(title, contentHtml, contentHtml.hashCode()+"");
	}
} //class