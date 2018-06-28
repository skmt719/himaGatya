package com.example.himaGatya.Controller.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.transaction.annotation.Transactional;

import com.example.himaGatya.userdata.repositories.*;

@Controller
public class RegisterController {
	
	@Autowired
	UserDataRepository repository;
	
	@GetMapping("/register")
	public ModelAndView register(@ModelAttribute("formModel") UserData userdata, ModelAndView mav) {
		mav.setViewName("auth/register");
		Iterable<UserData> list = repository.findAll();
		mav.addObject("userdatalist", list);
		return mav;
	}
	
	@Transactional(readOnly=false)
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ModelAndView form(
			@ModelAttribute("formModel") UserData userdata,
			ModelAndView mav) {
		repository.saveAndFlush(userdata);
		return new ModelAndView("redirect:/register");
	}
}
