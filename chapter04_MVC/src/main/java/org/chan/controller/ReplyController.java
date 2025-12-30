package org.chan.controller;

import org.chan.domain.ReplyVO;
import org.chan.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	// 1. 등록
	// consumes : 수신 데이터 포맷
	// produces : 송신 데이터 포맷
	@PostMapping(value = "/new", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> create(@RequestBody ReplyVO rvo) {
		log.info("ReplyVO : " + rvo);
		
		int insertCount = service.register(rvo);
		
		log.info("Reply Insert Count : " + insertCount);
		return insertCount == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) :
			new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
