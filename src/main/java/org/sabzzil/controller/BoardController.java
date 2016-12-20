package org.sabzzil.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.sabzzil.domain.BoardVO;
import org.sabzzil.domain.Criteria;
import org.sabzzil.domain.SearchCriteria;
import org.sabzzil.service.BoardService;
import org.sabzzil.util.MimeMediaUtil;
import org.sabzzil.util.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		return new ResponseEntity<>(
				UploadFileUtils.uploadFile(uploadPath, 
				file.getOriginalFilename(), 
				file.getBytes()), HttpStatus.CREATED);
	}
	
	@ResponseBody
	@RequestMapping(value = "/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType = MimeMediaUtil.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath + fileName);
			
			if(mType != null) {
				headers.setContentType(mType);
			} else {
				fileName = fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("content-Disposition", "attachment); filename=\"" +
							new String(fileName.getBytes("UTF-8"), "ISO-8859-1")
							+ "\"");
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
							headers, HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/getAttach/{bno}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("bno") int bno) throws Exception {
		return service.getAttach(bno);
	}
	
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName) {
		
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		MediaType mType = MimeMediaUtil.getMediaType(formatName);
		
		if(mType != null) {
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(uploadPath + (front + end).replace('/', File.separatorChar)).
					delete();
		}
		
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/modifyA", method = RequestMethod.POST)
	public String modifyGET(@ModelAttribute("boardVO") BoardVO boardVO, 
			@ModelAttribute("cri") SearchCriteria cri, 
			Model model) throws Exception {
		
		return "board/modify";
	}
	
	@RequestMapping(value = "/modifyPOST", method = RequestMethod.POST)
	public String modifyPOST(@ModelAttribute("boardVO") BoardVO boardVO, 
			@ModelAttribute("cri") SearchCriteria cri) throws Exception {
		
		service.modify(boardVO);

		return "redirect:/board/slist";
	}
	
}
