package com.example.himaGatya.Controller.Event;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

	// Impl 定義
	@Autowired
	CertificationsServiceImpl certificationsServiceImpl;
	@Autowired
<<<<<<< Updated upstream
	EventsServiceImpl eventServiceImpl;
=======
	Event_create_logsServiceImpl event_create_logsServiceImpl;
	@Autowired
	EventsServiceImpl eventServiceImpl;
	@Autowired
	GachaRollLogsServiceImpl gachaRollLogsServiceImpl;
	@Autowired
	UsersServiceImpl usersServiceImpl;
>>>>>>> Stashed changes


	////////////////////////////////////////////////////////
	//
	// データ確認用テストコード
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
	// 管理者側イベント一覧画面 (index)
	//
	////////////////////////////////////////////////////////

	@GetMapping("/admin")
	public String eventsList(Model model) {
		/*Events eventsForm = new Events();
		model.addAttribute("events",eventsForm);*/
		return "/event/index";
	}
	@PostMapping("/admin")
	public String eventsList(@ModelAttribute Events eventsForm) {
		// table に上書き
		eventServiceImpl.saveEvents(eventsForm);
		return "/event/index";
	}

	////////////////////////////////////////////////////////
	//
	// 管理者側イベント編集画面
	//
	////////////////////////////////////////////////////////
	// Get処理 画面表示　　二重に処理してしまう？
	@GetMapping("/admin/edit")
	public String eventsEdit(Model model) {
		Events eventsForm = new Events();
		model.addAttribute("eventsForm",eventsForm);
		return "/event/edit";
	}
	// データ登録 save ボタン処理
	@PostMapping(value="/admin/edit" , params="save")
	public String eventsEdit(@ModelAttribute Events eventsForm , BindingResult bindingresult) {
		// table に上書き
		eventServiceImpl.saveEvents(eventsForm);
		return "/event/index";
	}
	// データ削除
/*	@PostMapping("/admin/list")
	public String deleteData(@ModelAttribute Events eventsForm) {

		eventServiceImpl.deleteEventById(eventsForm.getId());
		return "/";
	}*/


}
