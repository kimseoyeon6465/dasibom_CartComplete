package com.booksajo.dasibom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping(value="/aboutus.do")
	public String aboutus() {
		return "aboutus";
	}

}