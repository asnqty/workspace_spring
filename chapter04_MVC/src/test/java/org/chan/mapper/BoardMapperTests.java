package org.chan.mapper;

import java.sql.Date;
import java.time.LocalDateTime;
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
public class BoardMapperTests {

	@Autowired
	private BoardMapper mapper;
	
//	@Test
//	public void testGetList() {
//		List<BoardVO> list = mapper.getList();
//		for(BoardVO vo : list) {
//			log.info(vo);
//		}
//	}
	
//	@Test
//	public void testInsert() {
//		BoardVO bvo = new BoardVO();
//		bvo.setTitle("테스트제목5");
//		bvo.setContent("테스트내용5");
//		bvo.setWriter("user05");
//		int result = mapper.insert(bvo);
//		log.info(result);
//	}
	
//	@Test
//	public void testRead() {
//		String title = "테스트제목";
//		BoardVO bvo = new BoardVO();
//		bvo = mapper.read(title);
//		log.info(bvo);
//	}
	
//	@Test
//	public void testDelete() {
//		int bno = 10;
//		int result = mapper.delete(bno);
//		log.info(result);
//	}
	
//	@Test
//	public void testUpdate() {
//		Date time = new Date(25, 11, 25);
//		BoardVO bvo = new BoardVO();
//		bvo.setBno(10);
//		bvo.setTitle("변경테스트제목5");
//		bvo.setContent("변경테스트내용5");
//		bvo.setWriter("변경user05");
//		bvo.setUpdatedate(time);
//		int result = mapper.update(bvo);
//		log.info(result);
//	}
}
