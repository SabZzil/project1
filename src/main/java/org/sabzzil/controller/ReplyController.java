package org.sabzzil.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sabzzil.domain.Criteria;
import org.sabzzil.domain.ReplyVO;
import org.sabzzil.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board/reply")
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	@RequestMapping(value = "/{bno}/{page}")
	public ResponseEntity<Map<String, Object>> 
		replylist(@PathVariable("bno") int bno, 
				@PathVariable("page") int page) throws Exception {

		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			Criteria cri = new Criteria();
			cri.setReplyPage(page);
			Map<String, Object> map = new HashMap<String, Object>();
			
			List<ReplyVO> list = service.list(bno, cri);
			map.put("replyList", list);
			map.put("cri", cri);
		
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<String> add(@RequestBody ReplyVO replyVO) throws Exception {
		
		ResponseEntity<String> entity = null;
		try {
			service.add(replyVO);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/delete/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rno") int rno) throws Exception {
		ResponseEntity<String> entity = null;
	
		try {
			service.delete(rno);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
