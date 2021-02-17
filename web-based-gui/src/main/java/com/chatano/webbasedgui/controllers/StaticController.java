package com.chatano.webbasedgui.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class StaticController {

	@RequestMapping(path="/")
	public ModelAndView welcome()
	{
		System.out.print("hello");
		return new ModelAndView("index");
	}
	
	@RequestMapping(path="/about")
	public ModelAndView about()
	{
		return new ModelAndView("about");
	}
	
	@RequestMapping(path="/terms")
	public ModelAndView terms()
	{
		return new ModelAndView("terms");
	}
	
	@RequestMapping(path="/privacy")
	public ModelAndView privacy()
	{
		return new ModelAndView("privacy");
	}
}
	