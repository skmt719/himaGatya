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
    CertificationsService CertificationService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginPost() {
        return "redirect:/login-error";
    }




    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "auth/register";
    }

    @PostMapping("/signup")
    public String signupPost(Model model, @Valid SignupForm signupForm, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        try {
            CertificationService.registerUser(signupForm.getUsername(), signupForm.getPassword(), signupForm.getMailAddress());
            
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("signupError", true);
            return "auth/register";
        }

        try {
            request.login(signupForm.getMailAddress(), signupForm.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "redirect:/home";
    }
    
   @GetMapping("/signup-error")
    public String SignupError(Model model) {
        model.addAttribute("signupError", true);
        return "auth/register";
    }

}