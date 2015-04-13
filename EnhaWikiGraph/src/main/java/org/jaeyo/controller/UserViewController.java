package org.jaeyo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jaeyo.model.WikiDoc;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserViewController {
	private static final Logger logger=LoggerFactory.getLogger(UserViewController.class);
	
	@RequestMapping(value="/UserView", method = RequestMethod.GET)
	public @ResponseBody String getUserView(HttpServletRequest request){
		String sessionId = request.getSession().getId();
		List<WikiDoc> wikiDocs = userViewRepo.loadBySessionId(sessionId);
		JSONArray jsonArr = new JSONArray();
		for(WikiDoc wikiDoc : wikiDocs)
			jsonArr.put(wikiDoc.getTitle());
		return jsonArr.toString();
//		TODO wiki graph
	} //home
} // class