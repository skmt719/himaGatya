package com.example.himaGatya.Controller.login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String index() {
		return "redirect:/home/1";
	}

	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}

	@PostMapping(value="/login" , params="login")
	public String loginPost() {
		return "redirect:/login";
	}
	@PostMapping(value="/login" , params="signUp")
	public String jumpToSignup(Model model) {

		return "signUp";
	}


/*	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "auth/login";
	}*/

	@GetMapping("/signUp")
	public String signup(Model model) {
		model.addAttribute("signupForm", new SignupForm());
		return "auth/register";
	}

	@PostMapping("/signUp")
	public String signupPost(Model model, @Valid SignupForm signupForm, BindingResult bindingResult, HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			return "auth/register";
		}

		try {
			userService.registerUser(signupForm.getUsername(), signupForm.getPassword(), signupForm.getMailAddress());
		}
		catch (DataIntegrityViolationException e) {
			model.addAttribute("signupError", true);
			return "auth/register";
		}

		try {
			request.login(signupForm.getMailAddress(), signupForm.getPassword());
		}
		catch (ServletException e) {
			e.printStackTrace();
		}

		return "redirect:/messages";
	}


}