package org.sabzzil.controller;

import javax.servlet.http.HttpSession;

import org.sabzzil.domain.UserVO;
import org.sabzzil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGet(@ModelAttribute("userVO") UserVO userVO) throws Exception {
		
	}
	
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPost(UserVO userVO, HttpSession session,
			Model model) throws Exception {
		UserVO vo = service.login(userVO);
		
		if(vo==null) {
			return;
		}
		
		model.addAttribute("userVO", vo);
	}
}
