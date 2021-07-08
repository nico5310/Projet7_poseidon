package com.nnk.springboot.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home Controller is CRUD methods for Home
 */
@Log4j2
@Controller
public class HomeController {

	/**
	 *Application homepage
	 * @return url home
	 */
	@RequestMapping("/")
	public String home(Model model) {
		log.info("Get home");
		return "home";
	}

	/**
	 * Admin home
	 * @return url bidList page
	 */
	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		log.info("Get admin home");
		return "redirect:/bidList/list";
	}


}
