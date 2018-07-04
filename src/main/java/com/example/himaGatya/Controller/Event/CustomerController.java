package com.example.himaGatya.Controller.Event;


import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.himaGatya.Controller.EventsForm;
import com.example.himaGatya.Controller.login.Certifications;

@Controller
public class CustomerController {

	// Impl 定義
	@Autowired
	Event_create_logsServiceImpl event_create_logsServiceImpl;
	@Autowired
	UsersServiceImpl usersServiceImpl;
	@Autowired
	EventsServiceImpl eventServiceImpl;
	@Autowired
	GachaRollLogsServiceImpl gachaRollLogsServiceImpl;

	// マクロ定義
	public static final int ROLL_COUNT = 3;

	////////////////////////////////////////////////////////
	//
	// ホーム画面1
	//
	////////////////////////////////////////////////////////
	// mypage①へ遷移
	@GetMapping("/home")
	public String home_one(Model model) {
		model.addAttribute("logedIn", true);
		return "home/home_1";
	}
	// mypage①へ遷移
//	@PostMapping(value="/home/1" , params="myPage")
//	public String home_one_jumpToMyPage(Model model) {
//		model.addAttribute("logedIn", true);
//		return "myPage/myPage_1";
//	}

//	@GetMapping("home/1")
//	public String myPage_two_jumpToHome1() {
//		return "home/home_1";
//	}

	////////////////////////////////////////////////////////
	//
	// ホーム画面2
	//
	////////////////////////////////////////////////////////
	@GetMapping("/home/2")
	public String home_two(Model model) {
		model.addAttribute("logedIn", true);
		return "home/home_2";
	}


	// ガチャを引いたとき
	@PostMapping(value="/home/2" , params="gacha")
	public String gachaRoll(
						Model model ,
						@AuthenticationPrincipal Certifications certifications,	// loginUserの情報
						List<GachaRollLogs> gachaRollLogs ,
						@ModelAttribute EventsForm form ,
						BindingResult bindingresult) {
		// 変数宣言
		Users users = usersServiceImpl.getUsersByName(certifications.getUsername());
		long userId = users.getId();								// userId取得
		List<Events> gachaResult = null;							// ガチャ結果を保存するリスト
        long[] EventIdLists = null;									// EventListsのidのみ取得する変数
		List<Events> EventLists = eventServiceImpl.getEventsList();	// 全イベントをリストとして取得
		int NumOfAllEvents = EventLists.size();						// イベントの総数を取得
		GachaRollLogs gachaVariable = null;							// ガチャログテーブルの一時的な保存用変数

		for(int l = 0; l < ROLL_COUNT; l ++) 						// 三回行う
		{	// 乱数生成(イベントの総数を超えないよう調整)
			Random rand = new Random();
	        int randomNum = rand.nextInt(NumOfAllEvents);			// 0番目のイベントも含める
	        for ( int i = 0; i < EventLists.size(); ++i ) 			// 全てのイベントの数だけループ
	        {
	        	EventIdLists[i] = EventLists.get( i ).getId();
	        }
	        gachaResult.add( eventServiceImpl.getEventsById(EventIdLists[randomNum]) );	// 乱数を用いてイベントのid取得、代入

	        // ログインしているユーザのidとイベント情報のidを登録
			gachaVariable.setUser_id(userId);
			gachaVariable.setEvent_id(gachaResult.get(l).getId());
			gachaRollLogsServiceImpl.saveGachaRollLogs(gachaVariable);	// 保存
		}

		// ガチャ演出画面へ遷移
		return "/gacha";
	}

	////////////////////////////////////////////////////////
	//
	// マイページ1
	//
	////////////////////////////////////////////////////////
//	@GetMapping("/myPage/1")
//	public String myPage_one(Model model) {
//		model.addAttribute("logedIn", true);
//		return "myPage/myPage_1";
//	}
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
	@GetMapping("myPage/history")
	public String myPage_two() {
		return "myPage/myPage_1";
	}

	@GetMapping("myPage/2")
	public String myPage_two_jumpToHome() {
		return "myPage/myPage_2";
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
		model.addAttribute("logedIn", true);
		return "/event/index";
	}
	// イベントの新規作成 jump edit page
	@PostMapping(value="/admin" , params="createNew")
	public String newEventCreate(Model model) {
		model.addAttribute("logedIn", true);
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
		model.addAttribute("logedIn", true);
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
		model.addAttribute("logedIn", true);
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
