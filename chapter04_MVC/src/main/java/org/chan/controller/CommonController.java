package org.chan.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class CommonController {
	@GetMapping("accessError")
	public String accessDenied(Authentication auth, Model model) {
		log.info("Access Denied : " + auth);
		model.addAttribute("msg", "Access Denide");
		return "/accessError";
	}
}
