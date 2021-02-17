package com.chatano.webbasedgui.controllers;

import com.chatano.webbasedgui.handler.FileHandler;
import com.chatano.webbasedgui.model.ContactUs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;


@Controller
@RequestMapping(path="/contact")
public class ContactUsController {

	@Autowired
	FileHandler fileHandler;

    @RequestMapping(method = RequestMethod.GET)
	public ModelAndView contactusForm(Model map)
	{
    	 return new ModelAndView("contact","contactus",new ContactUs());
	}
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView submitForm(@ModelAttribute("contactus")ContactUs contactus, 
    	      BindingResult result, ModelMap model)
    {
		contactus.setTimestamp(new Date());
    	fileHandler.writeIntoContactUs(contactus.toString());
    	return new ModelAndView("contact","contactus",new ContactUs(true));
    }
}
