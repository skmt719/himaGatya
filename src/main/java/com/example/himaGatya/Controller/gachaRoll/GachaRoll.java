package com.example.himaGatya.Controller.gachaRoll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.himaGatya.Controller.Event.Events;
import com.example.himaGatya.Controller.Event.EventsServiceImpl;

@RestController
public class GachaRoll {

	@Autowired
	public EventsServiceImpl service;

	@RequestMapping("/roll")
	public String indexs() {




		List<Events> customers = service.getEventsList();

		return customers.get(3).getName();
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