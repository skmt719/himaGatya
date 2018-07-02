package com.example.himaGatya.Controller.gachaRoll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.himaGatya.Controller.Event.Events;
import com.example.himaGatya.Controller.Event.EventsServiceImpl;
import com.example.himaGatya.Controller.Event.UsersRepository;


//DBからランダムなデータを取得する

@Controller
public class GachaRoll {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	public EventsServiceImpl service;
	private Object model;

	@GetMapping("home/roll")
	public String indexs() {
		List<Events> customers = service.getEventsList();

		return "home/index";
	}



//DBから取得したデータをホーム画面に表示する
	@GetMapping(value="/home")
	public ModelAndView display(ModelAndView model) {
		List<Events> customerstmp = service.getEventsList();
		List<Events> customers = new ArrayList<Events>();
		Events eventstmp = new Events();
		eventstmp = customerstmp.get(0);
		eventstmp.setCost(0);
		customers.add(eventstmp);
		for ( Events a: customers) {
			System.out.println(a.getName());
		}
		model.setViewName("home/index");
		model.addObject("customers",customers);
        return model;

	}



	}

/*@Controller
class GachaRoll{

	private static EventsServiceImpl service;

	@GetMapping("/roll")
	public String gacharoll(Model model){
		Random random = new Random();
		int num = random.nextInt(10);

		//Events event = new Events();



		List<Events> customers = service.getEventsList();


		model.addAttribute(customers.get(3).getName());

		return customers.get(3).getName();
	}
}*/