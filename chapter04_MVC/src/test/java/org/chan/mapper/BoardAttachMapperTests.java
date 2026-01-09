package org.chan.mapper;

import java.util.List;

import org.chan.domain.BoardAttachVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardAttachMapperTests {
	@Autowired
	private BoardAttachMapper mapper;
	
//	@Test
//	public void insertTest() {
//		BoardAttachVO bvo = new BoardAttachVO();
//		bvo.setFileName("테스트 파일 이름");
//		bvo.setUploadPath("20260109");
//		bvo.setUuid("1234");
//		bvo.setBno(95);
//		mapper.insert(bvo);
//	}
	
//	@Test
//	public void findByBnoTest() {
//		int bno = 95;
//		List<BoardAttachVO> list = mapper.findByBno(bno);
//		log.info(list);
//	}
	
//	@Test
//	public void deleteTest() {
//		String uuid = "1234";
//		mapper.delete(uuid);
//	}
}
