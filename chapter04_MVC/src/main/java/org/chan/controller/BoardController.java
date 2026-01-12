package org.chan.controller;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;

import org.chan.domain.BoardAttachVO;
import org.chan.domain.BoardVO;
import org.chan.domain.Criteria;
import org.chan.domain.PageDTO;
import org.chan.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	// 전체 데이터 조회
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("list..." + cri);
		
		// jsp에선 pageNum과 amount를 파라미터로 보냈기에 항상 String 타입이였음
		// 따라서 값을 받았는지 확인하기 위해 null과 비교했음
		// spring에서는 Criteria 객체를 받기에 해당 객체의 필드가 int 타입이라 기초값인 0과 비교를 함
		if(cri.getPageNum() == 0 || cri.getAmount() == 0) {
			// pageNum과 amount를 받지 못했기에 기본값으로 초기화를 직접 해줌
			cri.setPageNum(1);
			cri.setAmount(10);
		}
		
		// 해당 페이지에 보여줄 데이터
		model.addAttribute("list", service.getList(cri));
		
		// 전체 게시글 수
		int total = service.getTotal();
		log.info("total..." + total);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	// 게시글 등록 화면 이동
	@GetMapping("/register")
	public String registerPage() {
		return "/board/register";
	}
	
	// 게시글 등록
	@PostMapping("/register")
	public String register(BoardVO bvo, RedirectAttributes rttr) {
		log.info("register..." + bvo);
		
		if(bvo.getAttachList() != null) {
			bvo.getAttachList().forEach(attach -> log.info(attach));
		}
		
		service.register(bvo);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/board/list";
	}
	
	// 게시글 조회
	@GetMapping("/get")
	public String get(@RequestParam("bno") int bno, Model model) {
		log.info("get..." + bno);
		model.addAttribute("bvo", service.get(bno));
		return "/board/get";
	}
	
	// 게시글 수정 화면 이동
	@GetMapping("/modify")
	public String modifyPage(@RequestParam("bno") int bno, Model model) {
		log.info("modify..." + bno);
		model.addAttribute("bvo", service.get(bno));
		return "/board/modify";
	}
	// 게시글 조회와 게시글 수정 화면으로 이동하는 두 메소드는 같은 코드로 같은 동작을 한 후 서로 다른 목적지로 간다.
	// 반환 타입을 void로 하고 mapping을 배열로 하면 하나의 코드로 같은 동작을 한 후 서로 다른 목적지로 보낼 수 있다.
	// ex)@GetMapping({"/modify", "/get"})
		
	// 게시글 수정
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes rttr, @RequestParam("pageNum") int pageNum, 
			@RequestParam("amount") int amount, @RequestParam(value = "removeFile", required = false) List<String> removeFiles,
			@RequestParam(value = "removeUuid", required = false) List<String> removeUuid) {
		log.info("modify..." + bvo);
		log.info("pageNum... : " + pageNum);
		log.info("amount... : " + amount);
		rttr.addFlashAttribute("result", service.modify(bvo) ? "success" : "fail");
		rttr.addAttribute("pageNum", pageNum);
	    rttr.addAttribute("amount", amount);
	    
	    // 게시글 수정 시 삭제 했던 파일을 전부 삭제
	    if(removeFiles != null) {
	    	for(int i=0; i<removeFiles.size(); i++) {
	    		File file;
	    		
	    		try {
	    			file = new File("C:\\upload\\" + URLDecoder.decode(removeFiles.get(i), "utf-8"));
	    			file.delete();
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    	}
	    }
	    
	    // 게시글 수정 시 삭제했던 파일을 DB에 반영
	    if(removeUuid != null) {
	    	for(int i=0; i<removeUuid.size(); i++) {
	    		service.removeFile(removeUuid.get(i));
	    	}
	    }
	    
	    // 삭제된 파일을 DB에서도 삭제
	    
	    return "redirect:/board/list";
	}
	
	// 게시글 삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr, @RequestParam("pageNum") int pageNum) {
		log.info("remove..." + bno);
		rttr.addFlashAttribute("result", service.remove(bno) ? "success" : "fail");
		rttr.addAttribute("pageNum", pageNum);
	    rttr.addAttribute("amount", 10);
		return "redirect:/board/list";
	}
	
	// 첨부파일 리스트 가져오기
	@ResponseBody
	@GetMapping(value = "/getAttachList/{bno}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BoardAttachVO>> getAttachList(@PathVariable("bno") int bno) {
		return new ResponseEntity<List<BoardAttachVO>>(service.getAttachList(bno), HttpStatus.OK);
	}
	
}
