package org.jaeyo.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.jaeyo.service.TestService;
import org.jaeyo.service.WikiDocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	private static final Logger logger=LoggerFactory.getLogger(TestController.class);
	
	@Inject private TestService testService;
	@Inject private WikiDocService wikiDocService;
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("test");
		StringBuilder testWordSb = new StringBuilder();
		testWordSb.append("wikidoc save : ").append(wikiDocService.save(null)).append("\n");
		testWordSb.append("wikidoc isExists : ").append(wikiDocService.isExists("test")).append("\n");
		testWordSb.append("wikidoc load : ").append(wikiDocService.load("test").getContentHtml()).append("\n");
		mv.addObject("testword", testWordSb.toString());
		return mv;
	} //home
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView root(HttpServletRequest request){
		return home(request);
	} //home
} // class