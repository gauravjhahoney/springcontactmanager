package com.SmartContact.HomeController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SmartContact.Entities.Contact;
import com.SmartContact.Entities.user;
import com.SmartContact.dao.UserRepo;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	
	
	//private BCryptPasswordEncoder PasswordEncoder;
	
	@Autowired
	private UserRepo UserRepo;
	
	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title","Home- smart contact manager");
		return "home";
	}
	
	
	@RequestMapping("/about")
	public String about(Model model)
	{
		model.addAttribute("title","about- smart contact manager");
		return "about";
	}
	
	
	@RequestMapping("/signup")
	public String signup(Model model)
	{
		model.addAttribute("title","Signup- smart contact manager");
		model.addAttribute("user",new user());
		return "signup";
	}
	
	
	//handler for custom login
	@GetMapping("/signin")
	public String CustomLogin(Model model)
	{
		model.addAttribute("title","login-page");
		return "login";
	}
	
	//handler for register user
	@RequestMapping(value= "/do_register",method= RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") user user,BindingResult results, @RequestParam(value= "agreement",defaultValue="false") boolean agreement,Model model,HttpSession session)
	{
		try {
		if(!agreement)
		{
			System.out.println("you have not agreed terms and condition");
			throw new Exception("you have not agreed terms and condition");
		}
		
		if(results.hasErrors())
		{
			System.out.println("ERROR"+results.toString());
			model.addAttribute("user",user);
			return "signup";
		}
		
		user.setRole("ROLE_USER");
		user.setEnabled(true);
		
		user.setImageUrl("register.png");
		//user.setPassword(PasswordEncoder.encode(user.getPassword()));
		
		
		System.out.println("Agreement"+agreement);
		System.out.println("user"+user);
		
		user result=this.UserRepo.save(user);
		
		
		
		model.addAttribute("user",new user());
		session.setAttribute("message", new Message("successfully register","alert-success"));
		return "signup";
	}
		catch(Exception e)
		{
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("something went wrong"+e.getMessage(),"alert-danger"));
			
			return "signup";
		}
		
	}

}
