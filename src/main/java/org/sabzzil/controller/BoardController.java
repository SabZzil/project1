package org.sabzzil.controller;

import javax.annotation.Resource;

import org.sabzzil.domain.BoardVO;
import org.sabzzil.domain.Criteria;
import org.sabzzil.domain.SearchCriteria;
import org.sabzzil.service.BoardService;
import org.sabzzil.util.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {

		model.addAttribute("list", service.list(cri));
		return "board/list";
	}
	
	@RequestMapping(value = "/slist", method = RequestMethod.GET)
	public void slist(@ModelAttribute("cri") SearchCriteria cri, 
			Model model) throws Exception {
		model.addAttribute("list", service.sList(cri));
		
	}
	
	@RequestMapping(value = "/read")
	public String read(int bno, @ModelAttribute("cri") SearchCriteria cri, 
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
		return "redirect:/board/slist";
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST,
					produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		System.out.println(file.getOriginalFilename());
		
		return new ResponseEntity<>(
				UploadFileUtils.uploadFile(uploadPath, 
				file.getOriginalFilename(), 
				file.getBytes()), HttpStatus.CREATED);
	}
	
	@ResponseBody
	@RequestMapping(value = "/displayFile")
	public 
	
}
