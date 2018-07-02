package com.example.himaGatya.Controller.Event;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.himaGatya.Controller.EventsForm;

@Controller
public class CustomerController {

	// Impl 定義
	@Autowired
	Event_create_logsServiceImpl event_create_logsServiceImpl;
	@Autowired
	EventsServiceImpl eventServiceImpl;
	@Autowired
	GachaRollLogsServiceImpl gachaRollLogsServiceImpl;



	////////////////////////////////////////////////////////
	//
	// データ確認用テストコード test.html
	//
	////////////////////////////////////////////////////////
	// Certifications entity

	// Events entity
	@GetMapping("/view/events")
	public String eventCheck(Model model) {
		List<Events> customers = eventServiceImpl.getEventsList();
		model.addAttribute("customers", customers);
		return "/test";
	}

	////////////////////////////////////////////////////////
	//
	// ホーム画面1
	//
	////////////////////////////////////////////////////////
	@GetMapping("/home/1")
	public String home_one(Model model) {
		return "home/home_1";
	}
	// mypage①へ遷移
	@PostMapping(value="/home/1" , params="myPage")
	public String home_one_jumpToMyPage() {
		return "myPage/1";
	}

	////////////////////////////////////////////////////////
	//
	// ホーム画面2
	//
	////////////////////////////////////////////////////////
	@GetMapping("/home/2")
	public String home_two(Model model) {
		return "home/home_2";
	}
	// myPage①へ遷移
	@PostMapping(value="/home/2" , params="myPage")
	public String home_two_jumpToMyPage() {
		return "myPage/2";
	}

	////////////////////////////////////////////////////////
	//
	// マイページ1
	//
	////////////////////////////////////////////////////////
	@GetMapping("/myPage/1")
	public String myPage_one(Model model) {
		return "myPage/myPage_1";
	}
	// home①へ遷移
	@PostMapping(value="/myPage/1" , params="home")
	public String myPage_one_jumpToHome() {
		return "home/1";
	}


	////////////////////////////////////////////////////////
	//
	// マイページ2
	//
	////////////////////////////////////////////////////////
	@GetMapping("/myPage/2")
	public String myPage_two(Model model) {
		return "myPage/myPage_2";
	}
	// home②へ遷移
	@PostMapping(value="/myPage/2" , params="home")
	public String myPage_two_jumpToHome() {
		return "home/2";
	}


	////////////////////////////////////////////////////////
	//
	// 管理者側イベント一覧画面 	list
	//
	////////////////////////////////////////////////////////
	// 一覧表示
	@GetMapping("/admin")
	public String showEventsList(Model model ) {
		List<Events> customers = eventServiceImpl.getEventsList();
		model.addAttribute("customers", customers);
		return "/event/index";
	}
	// イベントの新規作成 jump edit page
	@PostMapping(value="/admin" , params="createNew")
	public String newEventCreate() {
		return "redirect:/admin/edit";
	}
	// イベントの編集 jump edit page
	@PostMapping(value="/admin" , params="update")
	public ModelAndView selectEventEdit(ModelAndView mav ,
								Model model ,
								@ModelAttribute Events events ,
								@ModelAttribute EventsForm form ,
								BindingResult bindingresult) {
		events = eventServiceImpl.getEventsById( form.getSelectedEvents() );	// form からidを取得し、eventServiceImplにてイベント本体を取得

		model.addAttribute("events" , events);
		mav.setViewName("event/edit");
		return mav;
	}
	// イベントの削除
	@PostMapping(value="/admin" , params="delete")
	public String selectEventDelete(@ModelAttribute EventsForm form, BindingResult bindingresult) {
		eventServiceImpl.deleteEventById(form.getSelectedEvents());	// table の指定イベントを削除
		return "redirect:/admin";
	}
	// ログアウト
	@PostMapping(value="/admin" , params="logout")
	public String LogoutButton(@ModelAttribute EventsForm form, BindingResult bindingresult) {
		// ログアウト処理


		return "redirect:/admin";
	}

	////////////////////////////////////////////////////////
	//
	// 管理者側イベント編集画面		edit
	//
	////////////////////////////////////////////////////////
	// Get処理 画面表示　　二重に処理してしまう？
	@GetMapping("/admin/edit")
	public String GetEventsEdit(Model model) {
		Events eventsForm = new Events();
		model.addAttribute("events",eventsForm);
		return "/event/edit";
	}
	// データ登録 save ボタン処理
	@PostMapping(value="/admin/edit" , params="save")
	public String eventsEdit(@ModelAttribute Events events , BindingResult bindingresult) {

		eventServiceImpl.saveEvents(events);	// table に上書き
		return "redirect:/admin";
	}
	// back処理 list に遷移
	@PostMapping(value="/admin/edit" , params="back")
	public String jumpEventsList() {
		return "redirect:/admin";
	}

}
