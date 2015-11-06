package com.sapestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NavigController {

		@RequestMapping(value="/search",method=RequestMethod.GET)
				public String search() 
				{
					
					return "searchBook";
				}

}
