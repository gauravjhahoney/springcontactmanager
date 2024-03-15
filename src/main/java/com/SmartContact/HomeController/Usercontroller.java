package com.SmartContact.HomeController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class Usercontroller {
	
	@RequestMapping("/index")
	public String dashboard()
	{
		return "normal/user_dashboard";
	}

}
