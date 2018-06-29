package com.example.himaGatya.sample;


import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//import com.example.himaGatya.sample.beans.TestForm;

@Controller
public class SessionController {
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value = {"/sessionTest"}, method = {RequestMethod.GET})
	public ModelAndView index(@ModelAttribute TestForm form) {
		
		// 生成
		ModelAndView mv = new ModelAndView();
		
		// テンプレートを指定
		mv.setViewName("index");
		
		// 日時を取得、設定
		mv.addObject("now", new Date().toString());
		
		// modelに設定して画面に表示するようにする
		mv.addObject("form", form);
		
		// 返却
		return mv;
	}
	
	// POST用のパラメータを受け取る
	@RequestMapping(value = {"/formPost"}, method = {RequestMethod.POST})
	public String postTest1(
			@ModelAttribute TestForm form) {
		
		// セッションへ保存
		session.setAttribute("form", form);
		
		// リダイレクト
		return "redirect:/formPostView";
	}
	
	// GET用のパラメータを受け取る
	@RequestMapping(value = {"/formPostView"}, method = {RequestMethod.GET})
	public ModelAndView getTest1() {
		
		// 生成
		ModelAndView mv = new ModelAndView();
		
		// テンプレートを指定
		mv.setViewName("test1/post");
		
		// セッションを取得
		TestForm form = (TestForm) session.getAttribute("form");
		if(null != form){
			// セッションよりデータを取得して設定
			mv.addObject("form", form);
		}
		
		// セッションクリア
		session.invalidate();
		
		// 返却
		return mv;
	}
	
}