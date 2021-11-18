package com.ssafy.happyhouse.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MovePageController {
	
	private static final Logger logger = LoggerFactory.getLogger(MovePageController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String deal(Locale locale, Model model) {
		return "/deal/search";
	}
	
	@RequestMapping(value = "/mvqna", method = RequestMethod.GET)
	public String qna() {
		return "qna";
	}
}
