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
	CertificationsServiceImpl certificationsServiceImpl;
	@Autowired
	Event_create_logsServiceImpl event_create_logsServiceImpl;
	@Autowired
	EventsServiceImpl eventServiceImpl;
	@Autowired
	GachaRollLogsServiceImpl gachaRollLogsServiceImpl;
	@Autowired
	UsersServiceImpl usersServiceImpl;



	////////////////////////////////////////////////////////
	//
	// データ確認用テストコード test.html
	//
	////////////////////////////////////////////////////////
	// Certifications entity
	@GetMapping("/view/certifications")
	public String lists(Model model) {
		List<Certifications> customers = certificationsServiceImpl.getCertificationsList();
		model.addAttribute("customers", customers);
		return "/test";
	}
	// Events entity
	@GetMapping("/view/events")
	public String eventCheck(Model model) {
		List<Events> customers = eventServiceImpl.getEventsList();
		model.addAttribute("customers", customers);
		return "/test";
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
