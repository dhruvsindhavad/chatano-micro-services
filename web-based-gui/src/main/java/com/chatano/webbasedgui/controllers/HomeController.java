package com.chatano.webbasedgui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(path="/chat", method=RequestMethod.GET)
	public ModelAndView clicked()
	{		
		return new ModelAndView("home", "ticket",restTemplate.getForObject("http://ticket-manager/ticket/generate/ChatAno",String.class));
	}


}
