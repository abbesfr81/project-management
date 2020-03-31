package com.jrp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.entities.UserAccount;
import com.jrp.pma.services.UserAccountService;

@Controller
@RequestMapping
public class SecurityController {
	
	@Autowired
	UserAccountService userAccountService;

	@Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		return "security/register";
	}
	
	
	@PostMapping("/save")
	public String saveUser(Model model, UserAccount userAccount) {
		// save to the database using an employee crud repository
		userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
		userAccountService.save(userAccount);
		
		return "redirect:/";
	}
	
}
