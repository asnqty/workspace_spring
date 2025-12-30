package org.chan.controller;

import org.chan.domain.TestVO;
import org.chan.domain.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/test")
@RestController
public class TestController {
	
	@GetMapping(value = "/getText", produces = "text/plain; charset=utf-8")
	public String getText() {
		log.info("Mime Type : " + MediaType.TEXT_PLAIN_VALUE);
		
		// 기존의 jsp 파일 이름이 아닌 순수 데이터를 전달
		return "안녕하세요";
	}
	
	@GetMapping(value = "/getObject", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public TestVO getObject() {
		return new TestVO(100, "kim");
	}
	
	/*
	 *  메소드를 만들고 URL에 맞게 요청해서 결과를 확인하시오(xml,json)
	 *  1. 요청 URL : /test/check
	 *  2. 파라미터 : 실수형 age
	 *  3. 리턴타입 : TestVO
	 *   - vo 객체 생성
	 *   - no 필드는 0으로 고정
	 *   - 전달 받은 age를 문자열로 name 필드에 저장
	 */
	
	// produces는 굳이 처리 안해도 URL에 json이나 xml로 요청하면 잘 작동한다.
	@GetMapping(value = "/check", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	// @RequestParam 어노테이션에 defaultValue나 required=false 등 처리가 가능하기에 써주는게 좋다.
	// 또한 매개변수로 받는 타입을 double이 아닌 Double의 rapper 클래스로 받으면 값을 받지 않아도 null을 감싸기 때문에 500 에러가 나지 않고 null로 표시된다.
	public ResponseEntity<TestVO> check(@RequestParam("age") double age) {
		TestVO vo = new TestVO();
		vo.setNo(0);
		vo.setName(String.valueOf(age));
		
		ResponseEntity<TestVO> result = null;
		
		// 자바스크립트에서 데이터 검증하는 과정이 복잡해서 HttpStatus만 확인하면 되게 미리 검증
		if(age>150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	
	// rest 방식에서 파라미터를 받는 방법 쿼리 스트링 방식이 아니며, 경로를 통해 받는다
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") int pid) {
		return new String[] {"category : " + cat + ", " + "productId : " + pid};
	}
	
	@PostMapping("/ticket")
	// @RequestBody 어노테이션이 json 타입의 데이터를 알아서 자바의 클래스 타입으로 형변환 해줌
	public Ticket convert(@RequestBody Ticket t) {
		log.info("convert... ticket : " + t);
		
		String result = new Gson().toJson(t);
		log.info(result);
		
		return t;
	}
	
	@GetMapping("/info")
	public String makeJson() {
		// gson의 클래스
		JsonObject json = new JsonObject();
		
		json.addProperty("name", "kim");
		json.addProperty("age", "10");
		json.addProperty("job", "student");
		
		JsonArray ja = new JsonArray();
		for(int i=0; i<5; i++) {
			JsonObject j = new JsonObject();
			j.addProperty("user" + i, i);
			ja.add(j);
		}
		json.add("users", ja);
		return json.toString();
	}
}
