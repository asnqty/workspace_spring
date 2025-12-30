package org.chan.service;

import java.sql.Date;
import java.util.List;

import org.chan.domain.ReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyServiceTest {
	
	@Autowired
	private ReplyService service;
	
//	@Test
//	public void testRegister() {
//		ReplyVO rvo = new ReplyVO();
//		rvo.setBno(97);
//		rvo.setReply("테스트댓글내용2");
//		rvo.setReplyer("테스트댓글작성자2");
//		service.register(rvo);
//	}
	
//	@Test
//	public void testGetList() {
//		List<ReplyVO> list = service.getList(97);
//		for(ReplyVO rvo : list) {
//			log.info(rvo);
//		}
//	}
	
//	@Test
//	public void testGet() {
//		ReplyVO rvo = service.get(3);
//		log.info(rvo);
//	}
	
//	@Test
//	public void testRemove() {
//		log.info(service.remove(3) ? "삭제 성공" : "삭제 실패");
//	}
	
//	@Test
//	public void testModify() {
//		ReplyVO rvo = new ReplyVO();
//		rvo.setRno(1);
//		rvo.setReply("테스트댓글내용");
//		log.info(service.modify(rvo) ? "수정 성공" : "수정 실패");
//	}
}
