package org.sabzzil.controller;

import org.sabzzil.domain.BoardVO;
import org.sabzzil.domain.Criteria;
import org.sabzzil.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {

		model.addAttribute("list", service.list(cri));
		return "board/list";
	}
	
	@RequestMapping(value = "/read")
	public String read(int bno, @ModelAttribute("cri") Criteria cri, 
			Model model) throws Exception {
		
		model.addAttribute("boardVO", service.read(bno));
		return "board/read";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void writeGet() {}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePost(BoardVO boardVO, RedirectAttributes rttr) throws Exception {
		
		service.create(boardVO);
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/board/list";
	}
}
