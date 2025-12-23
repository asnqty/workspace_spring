package org.chan.service;

import java.sql.Date;
import java.util.List;

import org.chan.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTests {
	
	@Autowired
	private BoardService service;
	
//	@Test
//	public void testGetList() {
//		List<BoardVO> list = service.getList();
//		for(BoardVO bvo : list) {
//			log.info(bvo);
//		}
//	}
	
//	@Test
//	public void testRegister() {
//		BoardVO bvo = new BoardVO();
//		bvo.setBno(10);
//		bvo.setTitle("테스트제목10");
//		bvo.setContent("테스트내용10");
//		bvo.setWriter("user10");
//		service.register(bvo);
//	}
	
//	@Test
//	public void testGet() {
//		int bno = 10;
//		BoardVO bvo = new BoardVO();
//		bvo = service.get(bno);
//		log.info(bvo);
//	}
	
//	@Test
//	public void testRemove() {
//		int bno = 10;
//		service.remove(bno);
//	}
	
//	@Test
//	public void testModify() {
//		BoardVO bvo = new BoardVO();
//		bvo.setBno(10);
//		bvo.setTitle("변경테스트제목10");
//		bvo.setContent("변경테스트내용10");
//		bvo.setWriter("변경user10");
//		Date time = new Date(25, 11, 25);
//		bvo.setUpdatedate(time);
//		service.modify(bvo);
//	}
}
