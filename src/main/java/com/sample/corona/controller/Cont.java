package com.sample.corona.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class Cont {

	@RequestMapping("/")
	public String h()
	{
		return "home.jsp";
	}
}
