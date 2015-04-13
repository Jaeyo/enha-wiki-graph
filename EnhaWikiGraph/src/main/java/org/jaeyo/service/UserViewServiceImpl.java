package org.jaeyo.service;

import java.util.List;

import javax.inject.Inject;

import org.jaeyo.model.WikiDoc;
import org.jaeyo.repo.UserViewRepository;
import org.jaeyo.util.InjectLogger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;

public class UserViewServiceImpl implements UserViewService{
	@InjectLogger Logger logger;
	@Inject UserViewRepository userViewRepo;
	@Inject WikiDocEdgesService wikiDocEdgesRepo;

	@Override
	public JSONObject getUserViewWithEdges(String sessionId) {
		JSONObject retJson = new JSONObject();
		
		TODO how to???
		
		JSONArray nodes = new JSONArray();
		List<WikiDoc> wikiDocs = userViewRepo.loadBySessionId(sessionId);
		for(WikiDoc wikiDoc : wikiDocs)
			nodes.put(wikiDoc.getTitle());
		
		int tt = wikiDocEdgesRepo.loadBySrc()
		
		return retJson;
	} //getUserViewWidhEdges
} //class