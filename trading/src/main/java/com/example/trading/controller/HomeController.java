package com.example.trading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping("/")
	public ModelAndView home(ModelAndView mav) {
		mav.setViewName("home");
		mav.addObject("message", "스프링 부트 애플리케이션");
		return mav;
	}
}
